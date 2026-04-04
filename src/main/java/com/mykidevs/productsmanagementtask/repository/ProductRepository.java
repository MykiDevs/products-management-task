package com.mykidevs.productsmanagementtask.repository;


import com.mykidevs.productsmanagementtask.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = {"producer"})
    @Query("SELECT p FROM Product p")
    Page<Product> findAllWithProducer(Pageable pageable);

    @EntityGraph(attributePaths = {"producer"})
    Optional<Product> findWithProducerById(Long id);
}
