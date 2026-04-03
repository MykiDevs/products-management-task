package com.mykidevs.productsmanagementtask.repository;

import com.mykidevs.productsmanagementtask.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Optional<Producer> getProducersById(Long id);
}
