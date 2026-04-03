package com.mykidevs.productsmanagementtask.mapper;

import com.mykidevs.productsmanagementtask.dto.request.ProductCreateRequest;
import com.mykidevs.productsmanagementtask.dto.response.ProductResponse;
import com.mykidevs.productsmanagementtask.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "producer", ignore = true)
    Product toEntity(ProductCreateRequest request);
    ProductResponse toDto(Product product);
}
