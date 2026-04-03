package com.mykidevs.productsmanagementtask.exception;


public class ProducerNotFoundException extends NotFoundException {
    public ProducerNotFoundException(Long id) {
        super("Producer with id " + id + "not found");
    }
}
