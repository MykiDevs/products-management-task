package com.mykidevs.productsmanagementtask.dto.response;

import java.math.BigDecimal;

public record ProductSummaryDto(
        Long id,
        String name,
        BigDecimal price
) {
    
}
