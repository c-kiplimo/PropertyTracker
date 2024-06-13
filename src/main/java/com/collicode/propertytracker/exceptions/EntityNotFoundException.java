package com.collicode.propertytracker.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String name) {
        super(name);
    }


    public static EntityNotFoundException notFound(String message) {
        return new EntityNotFoundException(message);
    }
}
