package com.mykidevs.productsmanagementtask.service;

import com.mykidevs.productsmanagementtask.dto.request.ProducerCreateRequest;
import com.mykidevs.productsmanagementtask.dto.request.ProducerPaginationRequest;
import com.mykidevs.productsmanagementtask.exception.InvalidSortPropertyException;
import com.mykidevs.productsmanagementtask.exception.ProducerNotFoundException;
import com.mykidevs.productsmanagementtask.mapper.ProducerMapper;
import com.mykidevs.productsmanagementtask.model.Producer;
import com.mykidevs.productsmanagementtask.repository.ProducerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@AllArgsConstructor
public class ProducerService {
    private final ProducerMapper producerMapper;
    private final ProducerRepository producerRepository;
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("name", "id");
    private Producer getByid(Long id) {
        return producerRepository.findById(id).orElseThrow(() -> new ProducerNotFoundException(id));
    }
    @Transactional
    public Producer create(ProducerCreateRequest req) {
        Producer producer = producerMapper.toEntity(req);
        return producerRepository.save(producer);
    }

    @Transactional(readOnly = true)
    public Producer get(Long id) {
        return getByid(id);
    }

    @Transactional(readOnly = true)
    public Page<Producer> getAll(ProducerPaginationRequest request) {
        if(!ALLOWED_SORT_FIELDS.contains(request.sortBy())) {
            throw new InvalidSortPropertyException(request.sortBy());
        }
        Pageable pageable = PageRequest.of(request.page(), request.size(), Sort.by(Sort.Direction.fromString(request.direction()), request.sortBy()));
        return producerRepository.findAll(pageable);
    }
    @Transactional
    public Producer update(Long id, ProducerCreateRequest req) {
        Producer oldProducer = getByid(id);
        oldProducer.setName(req.name());
        return producerRepository.save(oldProducer);
    }

    @Transactional
    public void delete(Long id) {
        producerRepository.deleteById(id);
    }

}
