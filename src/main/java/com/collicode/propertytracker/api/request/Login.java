package com.collicode.propertytracker.api.request;

import lombok.Data;

@Data
public class Login {

    private String username;
    private String password;
    private Boolean rememberMe;
}
