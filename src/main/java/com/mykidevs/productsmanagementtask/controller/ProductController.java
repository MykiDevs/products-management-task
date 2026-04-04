package com.mykidevs.productsmanagementtask.controller;


import com.mykidevs.productsmanagementtask.dto.request.ProducerPaginationRequest;
import com.mykidevs.productsmanagementtask.dto.request.ProductCreateRequest;
import com.mykidevs.productsmanagementtask.dto.request.ProductPaginationRequest;
import com.mykidevs.productsmanagementtask.dto.response.ProductResponseDto;
import com.mykidevs.productsmanagementtask.dto.response.ProductSummaryDto;
import com.mykidevs.productsmanagementtask.model.Producer;
import com.mykidevs.productsmanagementtask.model.Product;
import com.mykidevs.productsmanagementtask.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products/")
public class ProductController {
    private final ProductService productService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public ProductResponseDto createProduct(@RequestBody @Valid ProductCreateRequest request) {
        return productService.create(request);
    }
    @GetMapping
    public Page<ProductSummaryDto> getProductsWithPagination(@Valid ProductPaginationRequest request) {
        return productService.getAll(request);
    }
    @GetMapping("/{id}")
    public ProductResponseDto getProduct( @PathVariable Long id) {
        return productService.get(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody @Valid ProductCreateRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}
