package com.app.productservice.controllers;

import com.app.productservice.models.dto.ProductDTO;
import com.app.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<ProductDTO> getAll() {
        return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDTO>> getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @GetMapping("/price-range")
    public Flux<ProductDTO> getByPriceRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return productService.getProductByPriceRange(min, max);
    }

    @PostMapping
    public Mono<ProductDTO> createNewProduct(@RequestBody Mono<ProductDTO> productDTOMono) {
        return productService.createNewProduct(productDTOMono);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductDTO>> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDTO> productDTOMono) {
        return productService.updateProduct(id, productDTOMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProductById(id);
    }
}
