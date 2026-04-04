package com.mykidevs.productsmanagementtask.mapper;

import com.mykidevs.productsmanagementtask.dto.request.ProducerCreateRequest;
import com.mykidevs.productsmanagementtask.dto.response.ProducerResponseDto;
import com.mykidevs.productsmanagementtask.dto.response.ProducerSummaryDto;
import com.mykidevs.productsmanagementtask.model.Producer;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProducerMapper {
    Producer toEntity(ProducerCreateRequest request);
    ProducerResponseDto toResponseDto(Producer producer);
    ProducerSummaryDto toSummaryDto(Producer producer);
    List<ProducerSummaryDto> toDtoList(Page<Producer> producers);
}
