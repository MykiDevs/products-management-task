package com.mykidevs.productsmanagementtask.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Map;

@Setter
@Getter
@ToString(exclude = "producer")
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_id")
    @SequenceGenerator(sequenceName = "product_seq_id", allocationSize = 10, name = "product_seq_id")
    @Column(name = "id")
    private Long id;

    @Size(min = 5, max = 255, message = "Name must be between 5 and 255 characters")
    @NotBlank(message = "Name can't be blank")
    private String name;
    @Min(value = 1, message = "Product price must be more than 1 dollar")
    @NotNull(message = "Price can't be null")
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producer_id")
    private Producer producer;
    @Size(max = 100, message = "100 attributes is max value!")
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    @NotNull(message = "Please add some attributes")
    private Map<String, Object> attributes;
}
