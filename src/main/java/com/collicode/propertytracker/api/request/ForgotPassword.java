package com.collicode.propertytracker.api.request;

import lombok.Data;

@Data
public class ForgotPassword {
    public String msisdn;
    public String otp;
    public String password;
}
