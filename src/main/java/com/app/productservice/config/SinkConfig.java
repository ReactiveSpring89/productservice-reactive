package com.app.productservice.config;

import com.app.productservice.models.dto.ProductDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class SinkConfig {

    @Bean
    public Sinks.Many<ProductDTO> sink() {
        return Sinks.many().replay().limit(1);
    }

    @Bean
    public Flux<ProductDTO> productBroadcast(Sinks.Many<ProductDTO> sinks) {
        return sinks.asFlux();
    }
}
