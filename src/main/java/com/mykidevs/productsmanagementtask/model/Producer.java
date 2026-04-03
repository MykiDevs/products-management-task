package com.mykidevs.productsmanagementtask.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 5, max = 255, message = "Name must be between 5 and 255 characters")
    @NotBlank(message = "Name can't be blank")
    private String name;
    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();
}
