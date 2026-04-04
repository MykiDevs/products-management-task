package com.mykidevs.productsmanagementtask.service;


import com.mykidevs.productsmanagementtask.dto.request.ProductPaginationRequest;
import com.mykidevs.productsmanagementtask.dto.request.ProductCreateRequest;
import com.mykidevs.productsmanagementtask.dto.response.ProductResponseDto;
import com.mykidevs.productsmanagementtask.dto.response.ProductSummaryDto;
import com.mykidevs.productsmanagementtask.exception.InvalidSortPropertyException;
import com.mykidevs.productsmanagementtask.exception.ProducerNotFoundException;
import com.mykidevs.productsmanagementtask.exception.ProductNotFoundException;
import com.mykidevs.productsmanagementtask.mapper.ProductMapper;
import com.mykidevs.productsmanagementtask.model.Producer;
import com.mykidevs.productsmanagementtask.model.Product;
import com.mykidevs.productsmanagementtask.repository.ProducerRepository;
import com.mykidevs.productsmanagementtask.repository.ProductRepository;
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
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;
    private final static Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "price", "name");
    private Product getByid(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }
    @Transactional
    public ProductResponseDto create(ProductCreateRequest req) {
        Product product = productMapper.toEntity(req);
        Producer producer = producerRepository.findById(req.producerId()).orElseThrow(() -> new ProducerNotFoundException(req.producerId()));
        product.setProducer(producer);
        return productMapper.toResponseDto(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    public ProductResponseDto get(Long id) {
        return productMapper.toResponseDto(getByid(id));
    }

    @Transactional(readOnly = true)
    public Page<ProductSummaryDto> getAll(ProductPaginationRequest request) {
        if(!ALLOWED_SORT_FIELDS.contains(request.sortBy())) {
            throw new InvalidSortPropertyException(request.sortBy());
        }
        Pageable pageable = PageRequest.of(request.page(), request.size(), Sort.by(Sort.Direction.fromString(request.direction()), request.sortBy()));
        return productRepository.findAllWithProducer(pageable).map(productMapper::toSummaryDto);
    }
    @Transactional
    public ProductResponseDto update(Long id, ProductCreateRequest req) {
        Producer producer = producerRepository.findById(req.producerId()).orElseThrow(() -> new ProducerNotFoundException(id));
        Product oldProduct = getByid(id);
        oldProduct.setAttributes(req.attributes());
        oldProduct.setName(req.name());
        oldProduct.setPrice(req.price());
        oldProduct.setProducer(producer);
        return productMapper.toResponseDto(productRepository.save(oldProduct));
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
