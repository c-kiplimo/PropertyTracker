package com.collicode.propertytracker.security;


import static com.collicode.propertytracker.config.Constants.HEADER_STRING;
import static com.collicode.propertytracker.config.Constants.TOKEN_PREFIX;

import com.collicode.propertytracker.infrastructure.model.User;
import com.collicode.propertytracker.infrastructure.model.UserSession;
import com.collicode.propertytracker.infrastructure.repository.UserSessionRepository;
import com.collicode.propertytracker.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    @Autowired
    UserSessionRepository userSessionRepository;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain chain)
            throws IOException, ServletException {

        String username = null;
        String authToken = resolveAuthToken(request);
        if (StringUtils.hasText(authToken)) {
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
                log.info("Found username {} ", username);
            } catch (final IllegalArgumentException e) {
                logger.error("An error occurred during getting username from token {}", e.getCause());
            } catch (final ExpiredJwtException e) {
                logger.info("User token has expired");
                username = jwtTokenUtil.getUsernameUnlimitedSkew(authToken);
                final User user = userService.findByMsisdn(username).get();
                UserSession userSession = userSessionRepository.findByUserId(user.getId());
                userSession.setLoggedIn(0);
                userSessionRepository.save(userSession);
                username = null;
            } catch (final SignatureException e) {
                logger.error("SignatureException. {}", e.getCause());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication()==null) {

            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {

                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

    private String resolveAuthToken(HttpServletRequest request) {
        String authToken = request.getHeader(HEADER_STRING);
        if (StringUtils.hasText(authToken) && authToken.startsWith(TOKEN_PREFIX)) {
            return authToken.substring(7);
        }

        return null;
    }
}
