package com.mykidevs.productsmanagementtask.dto.response;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public record ProductResponse(
        Long id,
        String name,
        BigDecimal price,
        Map<String, Object> attributes
) {
}
