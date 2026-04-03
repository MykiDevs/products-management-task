package com.mykidevs.productsmanagementtask.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public record ProducerResponse(
        Long id,
        String name
) {
}
