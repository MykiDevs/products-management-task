package com.mykidevs.productsmanagementtask.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "products")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producers")
public class Producer {

    @Id
    @GeneratedValue(generator = "producer_seq_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "producer_seq_id", sequenceName = "producer_seq_id", allocationSize = 10)
    private Long id;


    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();
}
