package com.mykidevs.productsmanagementtask.repository;

import com.mykidevs.productsmanagementtask.model.Producer;
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
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    @EntityGraph(attributePaths = {"products"})
    @Query("SELECT p FROM Producer p")
    Page<Producer> findAllWithProducts(Pageable pageable);

    @EntityGraph(attributePaths = {"products"})
    Optional<Producer> findWithProducerById(Long id);
}
