package org.example.Feign;

import org.example.DTO.ProductDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @GetMapping("/api/products/{productId}")
    ProductDTO getProduct(@PathVariable Long productId);

    @PutMapping("/api/products/{productId}/stock")
    ProductDTO updateStock(@PathVariable Long productId, @RequestParam Integer quantity);
}