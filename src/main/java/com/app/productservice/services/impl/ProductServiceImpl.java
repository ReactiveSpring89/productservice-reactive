package com.app.productservice.services.impl;

import com.app.productservice.models.dto.ProductDTO;
import com.app.productservice.repositories.ProductRepository;
import com.app.productservice.services.ProductService;
import com.app.productservice.utils.DTOEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Sinks.Many<ProductDTO> sinks;


    @Override
    public Flux<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .map(DTOEntityMapper::toDto);
    }

    @Override
    public Flux<ProductDTO> getProductByPriceRange(double min, double max) {
        return productRepository.findByPriceBetween(Range.closed(min, max))
                .map(DTOEntityMapper::toDto);
    }

    @Override
    public Mono<ProductDTO> getProductById(String id) {
        return productRepository.findById(id)
                .map(DTOEntityMapper::toDto);
    }

    @Override
    public Mono<ProductDTO> createNewProduct(Mono<ProductDTO> productDTO) {
        return productDTO.map(DTOEntityMapper::toEntity)
                .flatMap(productRepository::insert)
                .map(DTOEntityMapper::toDto)
                .doOnNext(sinks::tryEmitNext);
    }

    @Override
    public Mono<ProductDTO> updateProduct(String id, Mono<ProductDTO> productDTOMono) {
        return productRepository.findById(id)
                .flatMap(product -> productDTOMono
                        .map(DTOEntityMapper::toEntity)
                        .doOnNext(e -> e.setId(id))
                ).flatMap(productRepository::save)
                .map(DTOEntityMapper::toDto);
    }

    @Override
    public Mono<Void> deleteProductById(String id) {
       return productRepository.deleteById(id);
    }


}
