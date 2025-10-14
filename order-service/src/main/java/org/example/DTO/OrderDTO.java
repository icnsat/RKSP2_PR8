package org.example.DTO;

import lombok.Builder;
import lombok.Data;
import org.example.Model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderDTO {
    private Long id;
    private Long userId;
    private String userName;        // из User Service
    private Long productId;
    private String productName;     // из Product Service
    private BigDecimal productPrice; // из Product Service
    private Integer quantity;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;
}