package com.mykidevs.productsmanagementtask.exception;



public class InvalidSortPropertyException extends RuntimeException{
    public InvalidSortPropertyException(String sortProperty) {
        super("Invalid sort property: " + sortProperty);
    }

}
