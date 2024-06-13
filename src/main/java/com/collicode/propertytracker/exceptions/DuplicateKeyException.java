package com.collicode.propertytracker.exceptions;

public class DuplicateKeyException extends RuntimeException {

    public DuplicateKeyException(String name) {
        super(name);
    }


    public DuplicateKeyException(long id) {
        super(String.valueOf(id));
    }

    public static DuplicateKeyException exception(String message) {
        return new DuplicateKeyException(message);
    }

    public static void fire(String message) {
        throw new DuplicateKeyException(message);
    }
}
