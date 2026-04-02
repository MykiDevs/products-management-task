package com.mykidevs.productsmanagementtask;

import org.springframework.boot.SpringApplication;

public class TestProductsManagementTaskApplication {

    public static void main(String[] args) {
        SpringApplication.from(ProductsManagementTaskApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
