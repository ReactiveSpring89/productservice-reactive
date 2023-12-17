package com.app.productservice.services;

import com.app.productservice.models.dto.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductDTO> getAllProducts();

    Flux<ProductDTO> getProductByPriceRange(double min, double max);

    Mono<ProductDTO> getProductById(String id);

    Mono<ProductDTO> createNewProduct(Mono<ProductDTO> productDTO);

    Mono<ProductDTO> updateProduct(String id, Mono<ProductDTO> productDTOMono);
    
    Mono<Void> deleteProductById(String id);
}
