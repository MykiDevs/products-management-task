package com.mykidevs.productsmanagementtask.dto.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ProductPaginationRequest(
        @NotNull(message = "Page number can't be null")
        @Min(value = 0, message = "Page number can't be below zero")
        Integer page,

        @Min(value = 1, message = "Page size must be at least 1")
        @Max(value = 100, message = "Page size must be less than 100")
        Integer size,

        @Pattern(regexp = "ASC|DESC|asc|desc", message = "Sort direction must be ASC or DESC")
        String direction,
        String sortBy
) {
    public ProductPaginationRequest {
        if (page == null) page = 0;
        if (size == null) size = 10;
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }
        if(direction == null || direction.isEmpty()) {
            direction = "ASC";
        }
    }
}

