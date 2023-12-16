package com.app.productservice.models.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String id;
    private String description;
    private Double price;
}
