package com.collicode.propertytracker.notification.emailtemplate;

public class OTPUtil {

    public static String getOTPEmailBody(String title, String recipientName, String action, String otp) {

        return  "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Bake Connect: OTP</title><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700,800,900&display=swap\" rel=\"stylesheet\"></head>\n" +
                "<body style=\"background: #F2F2F3; padding: 40px 20px;\"><div style=\"width: 100%; max-width: 500px; margin: 0 auto; background-color: #ffffff; padding: 20px; box-sizing: border-box;\"><div style=\"width: 20%; max-width: 200px; margin: 0 auto; display: block; padding:0 0 0 10px; margin: 0;\"><img style=\"width: 100%; flex-shrink: 0;\" src=\"https://bakeconnect.app/assets/images/bake-connect-logo-full.png\" alt=\"bake connect logo\"></div><div style=\"padding: 20px; box-sizing: border-box; font-family: Roboto;\">" +
                "<h2 style=\"color: #000;font-family: Roboto;font-size: 16px;font-style: normal;font-weight: 600;line-height: 20px;margin-top: 0;\">"+title+"</h2>" +
                "<p style=\"padding: 0; color: #6F6C81; font-family: Roboto; font-size: 14px; font-style: normal; font-weight: 400;margin: 3px;\">Hi, "+recipientName+",</p>" +
                "<p style=\"padding: 0; color: #6F6C81; font-family: Roboto; font-size: 14px; font-style: normal; font-weight: 400;margin: 3px;\"> Use this one time password (OTP) to "+action+" </p>" +
                "<div style=\"text-align: center; margin: 20px 0; border-radius: 2px; background: #F3F4F8; padding: 30px; flex-shrink: 0; align-items: center;\">" +
                "<h1 style=\"font-size: 32px; font-weight: 600; color: #000000; margin: 0; text-align: center; font-family: Roboto; font-style: normal; font-weight: 600;\">"+otp+"</h1></div>" +
                "<p style=\"font-size: 12px;font-weight: 700;font-family: Roboto; line-height: 20px; color: #6F6C81;\">Kindly ignore if you didn't Initiate this action.</p></div></div></body></html>";
    }
}
