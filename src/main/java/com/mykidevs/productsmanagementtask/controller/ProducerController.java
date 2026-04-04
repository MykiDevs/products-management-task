package com.mykidevs.productsmanagementtask.controller;

import com.mykidevs.productsmanagementtask.dto.request.ProducerCreateRequest;
import com.mykidevs.productsmanagementtask.dto.request.ProducerPaginationRequest;
import com.mykidevs.productsmanagementtask.dto.response.ProducerResponseDto;
import com.mykidevs.productsmanagementtask.dto.response.ProducerSummaryDto;
import com.mykidevs.productsmanagementtask.model.Producer;
import com.mykidevs.productsmanagementtask.service.ProducerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/producers/")
public class ProducerController {
    private final ProducerService producerService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public ProducerResponseDto createProducer(@RequestBody @Valid ProducerCreateRequest request) {
        return producerService.create(request);
    }


    @GetMapping
    public Page<ProducerSummaryDto> getProducersWithPagination(@Valid ProducerPaginationRequest request) { // without @ResponseEntity cause it does not create ppr
        return producerService.getAll(request);
    }

    @GetMapping("/{id}")
    public ProducerResponseDto getProducer( @PathVariable Long id) {
        return producerService.get(id);
    }

    @PutMapping("/{id}")
    public ProducerResponseDto updateProducer(@PathVariable Long id, @RequestBody @Valid ProducerCreateRequest request) {
        return producerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProducer(@PathVariable Long id) {
        producerService.delete(id);
    }
}
