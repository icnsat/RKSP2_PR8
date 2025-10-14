package org.example.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductDTO {
    @NotBlank
    private String name;

    private String description;

    @Positive
    private BigDecimal price;

    @PositiveOrZero
    private Integer stockQuantity;

    private String category;
    private String imageUrl;
}