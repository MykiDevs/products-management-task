package com.mykidevs.productsmanagementtask.dto.response;

import java.math.BigDecimal;
import java.util.Map;

public record ProductResponseDto(
        Long id,
        String name,
        BigDecimal price,
        ProducerSummaryDto producer
) {
}
