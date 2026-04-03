package com.mykidevs.productsmanagementtask.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Map;

public record ProducerCreateRequest(
        @Size(min = 5, max = 255, message = "Name must be between 5 and 255 characters")
        @NotNull(message = "Name can't be null")
        String name
) {
}
