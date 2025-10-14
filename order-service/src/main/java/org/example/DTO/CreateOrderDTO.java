package org.example.DTO;

import lombok.Data;

@Data
public class CreateOrderDTO {
    private Long userId;
    private Long productId;
    private Integer quantity;
}