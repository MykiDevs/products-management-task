package com.mykidevs.productsmanagementtask.service;


import com.mykidevs.productsmanagementtask.dto.request.ProductPaginationRequest;
import com.mykidevs.productsmanagementtask.dto.request.ProductCreateRequest;
import com.mykidevs.productsmanagementtask.dto.response.ProducerResponse;
import com.mykidevs.productsmanagementtask.dto.response.ProductResponse;
import com.mykidevs.productsmanagementtask.exception.InvalidSortPropertyException;
import com.mykidevs.productsmanagementtask.exception.ProducerNotFoundException;
import com.mykidevs.productsmanagementtask.exception.ProductNotFoundException;
import com.mykidevs.productsmanagementtask.mapper.ProductMapper;
import com.mykidevs.productsmanagementtask.model.Producer;
import com.mykidevs.productsmanagementtask.model.Product;
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
    public Product create(ProductCreateRequest req) {
        Product product = productMapper.toEntity(req);
        Producer producer = producerRepository.findById(req.producerId()).orElseThrow(() -> new ProducerNotFoundException(req.producerId()));
        product.setProducer(producer);
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product get(Long id) {
        return getByid(id);
    }

    @Transactional(readOnly = true)
    public Page<Product> getAll(ProductPaginationRequest request) {
        if(!ALLOWED_SORT_FIELDS.contains(request.sortBy())) {
            throw new InvalidSortPropertyException(request.sortBy());
        }
        Pageable pageable = PageRequest.of(request.page(), request.size(), Sort.by(Sort.Direction.fromString(request.direction()), request.sortBy()));
        return productRepository.findAll(pageable);
    }
    @Transactional
    public Product update(Long id, ProductCreateRequest req) {
        Producer producer = producerRepository.findById(req.producerId()).orElseThrow(() -> new ProducerNotFoundException(id));
        Product oldProduct = getByid(id);
        oldProduct.setAttributes(req.attributes());
        oldProduct.setName(req.name());
        oldProduct.setPrice(req.price());
        oldProduct.setProducer(producer);
        return productRepository.save(oldProduct);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
