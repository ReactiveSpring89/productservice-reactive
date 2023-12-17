package com.app.productservice.services;


import com.app.productservice.models.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DataSetupService implements CommandLineRunner {
    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        ProductDTO p1 = new ProductDTO("4k-Tv", 10000d);
        ProductDTO p2 = new ProductDTO("Rajasthani Wallhanging", 200d);
        ProductDTO p3 = new ProductDTO("Abstract design curtain", 1000d);
        ProductDTO p4 = new ProductDTO("Stackable Marble theme centre table", 500d);

        Flux.just(p1, p2, p3, p4)
                .flatMap(p -> productService.createNewProduct(Mono.just(p)))
                .subscribe(System.out::println);
    }
}
