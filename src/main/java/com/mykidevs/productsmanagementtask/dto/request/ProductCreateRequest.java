package com.mykidevs.productsmanagementtask.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Map;

public record ProductCreateRequest(
        @Size(min = 5, max = 255, message = "Name must be between 5 and 255 characters")
        @NotNull(message = "Name can't be null")
        String name,
        @Min(value = 1, message = "Product price must be more than 1 dollar")
        @NotNull(message = "Price can't be null")
        BigDecimal price,
        @NotNull(message = "Producer id can't be null!")
        Long producerId,
        @Size(max = 100, message = "100 attributes is max value!")
        @NotNull(message = "Please add some attributes")
        Map<String, Object> attributes
) {
}
