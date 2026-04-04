package com.mykidevs.productsmanagementtask.service;

import com.mykidevs.productsmanagementtask.dto.request.ProducerCreateRequest;
import com.mykidevs.productsmanagementtask.dto.request.ProducerPaginationRequest;
import com.mykidevs.productsmanagementtask.dto.response.ProducerResponseDto;
import com.mykidevs.productsmanagementtask.dto.response.ProducerSummaryDto;
import com.mykidevs.productsmanagementtask.dto.response.ProductResponseDto;
import com.mykidevs.productsmanagementtask.exception.InvalidSortPropertyException;
import com.mykidevs.productsmanagementtask.exception.ProducerNotFoundException;
import com.mykidevs.productsmanagementtask.mapper.ProducerMapper;
import com.mykidevs.productsmanagementtask.mapper.ProductMapper;
import com.mykidevs.productsmanagementtask.model.Producer;
import com.mykidevs.productsmanagementtask.repository.ProducerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProducerService {
    private final ProducerMapper producerMapper;
    private final ProducerRepository producerRepository;
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("name", "id");
    private final ProductMapper productMapper;

    private Producer getByid(Long id) {
        return producerRepository.findById(id).orElseThrow(() -> new ProducerNotFoundException(id));
    }
    @Transactional
    public ProducerResponseDto create(ProducerCreateRequest req) {
        Producer producer = producerMapper.toEntity(req);
        return producerMapper.toResponseDto(producerRepository.save(producer));
    }

    @Transactional(readOnly = true)
    public ProducerResponseDto get(Long id) {
        return producerMapper.toResponseDto(getByid(id));
    }

    @Transactional(readOnly = true)
    public Page<ProducerSummaryDto> getAll(ProducerPaginationRequest request) {
        if(!ALLOWED_SORT_FIELDS.contains(request.sortBy())) {
            throw new InvalidSortPropertyException(request.sortBy());
        }
        Pageable pageable = PageRequest.of(request.page(), request.size(), Sort.by(Sort.Direction.fromString(request.direction()), request.sortBy()));
        return producerRepository.findAllWithProducts(pageable).map(producerMapper::toSummaryDto);
    }

    @Transactional
    public ProducerResponseDto update(Long id, ProducerCreateRequest req) {
        Producer oldProducer = getByid(id);
        oldProducer.setName(req.name());

        return producerMapper.toResponseDto(producerRepository.save(oldProducer));
    }

    @Transactional
    public void delete(Long id) {
        producerRepository.deleteById(id);
    }

}
