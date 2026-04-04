package com.mykidevs.productsmanagementtask.dto.response;

import java.util.List;

public record ProducerResponseDto(
        Long id,
        String name,
        List<ProductSummaryDto> products
) {
}
