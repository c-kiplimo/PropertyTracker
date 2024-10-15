package com.collicode.propertytracker.service.util;

import org.slf4j.Logger;

public class HexUtil {
    private static final long largeNumber = 100_000_000;

    public static String getHex(Long id, Logger logger) {
        try {
            return Long.toHexString(largeNumber - id);
        }catch (Exception ex){
            logger.error("Encountered exception", ex);
            return null;
        }
    }


    public static Long getId(String hex, Logger logger) {
        try {
            return (largeNumber - Long.parseLong(hex, 16));
        }catch (Exception e){
            logger.error("Encountered exception", e);
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(Long.toHexString(100_000_001)+"03");
    }
}
