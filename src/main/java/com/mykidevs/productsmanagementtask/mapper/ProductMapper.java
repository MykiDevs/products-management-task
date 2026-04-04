package com.mykidevs.productsmanagementtask.mapper;

import com.mykidevs.productsmanagementtask.dto.request.ProductCreateRequest;
import com.mykidevs.productsmanagementtask.dto.response.ProductResponseDto;
import com.mykidevs.productsmanagementtask.dto.response.ProductSummaryDto;
import com.mykidevs.productsmanagementtask.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "producer", ignore = true)
    Product toEntity(ProductCreateRequest request);
    ProductResponseDto toResponseDto(Product product);
    ProductSummaryDto toSummaryDto(Product product);
    List<ProductSummaryDto> toDtoList(List<Product> product);
}
