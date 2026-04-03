package com.mykidevs.productsmanagementtask.mapper;

import com.mykidevs.productsmanagementtask.dto.request.ProducerCreateRequest;
import com.mykidevs.productsmanagementtask.dto.request.ProductCreateRequest;
import com.mykidevs.productsmanagementtask.dto.response.ProducerResponse;
import com.mykidevs.productsmanagementtask.dto.response.ProductResponse;
import com.mykidevs.productsmanagementtask.model.Producer;
import com.mykidevs.productsmanagementtask.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {
    Producer toEntity(ProducerCreateRequest request);
    ProducerResponse toDto(Producer producer);
}
