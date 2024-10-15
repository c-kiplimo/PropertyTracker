package com.collicode.propertytracker.api.request;

import com.collicode.propertytracker.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginToken {

    private UserDTO user;
    private String token;
}
