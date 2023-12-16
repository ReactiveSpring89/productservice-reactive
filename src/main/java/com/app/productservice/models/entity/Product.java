package com.app.productservice.models.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {
    @Id
    private String id;
    private String description;
    private Double price;
}
