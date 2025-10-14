package org.example.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.DTO.CreateProductDTO;
import org.example.DTO.ProductDTO;
import org.example.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Product management APIs")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Get all products")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ProductDTO getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @Operation(summary = "Create new product")
    public ProductDTO createProduct(@RequestBody CreateProductDTO dto) {
        return productService.createProduct(dto);
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get products by category")
    public List<ProductDTO> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    @PutMapping("/{id}/stock")
    @Operation(summary = "Update product stock")
    public ProductDTO updateStock(@PathVariable Long id, @RequestParam Integer quantity) {
        return productService.updateStock(id, quantity);
    }
}