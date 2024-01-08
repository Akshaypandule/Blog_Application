package com.blogApplication.exception;

public class ResourceNotFoundExceptionString extends RuntimeException{

    String resourceName;
    String fieldName;
    String fieldDetails;

    public ResourceNotFoundExceptionString(String resourceName,String fieldName,String fieldDetails)
    {
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldDetails)) ;
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldDetails=fieldDetails;
    }

}
