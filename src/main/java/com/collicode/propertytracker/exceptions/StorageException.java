package com.collicode.propertytracker.exceptions;

public class StorageException extends RuntimeException {

    public StorageException(String name) {
        super(name);
    }


    public static StorageException exception(String message) {
        return new StorageException(message);
    }

    public static void fire(String message) {
        throw new StorageException(message);
    }
}
