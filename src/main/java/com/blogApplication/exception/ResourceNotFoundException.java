package com.blogApplication.exception;

public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
    String filedName;
    long filedValue;

    public ResourceNotFoundException(String resourceName, String filedName, long filedValue) {
        super(String.format("%s not found with %s: %s", resourceName, filedName, filedValue));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.filedValue = filedValue;
    }
}
