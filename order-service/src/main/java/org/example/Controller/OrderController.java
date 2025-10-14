package org.example.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.DTO.CreateOrderDTO;
import org.example.DTO.OrderDTO;
import org.example.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Order management APIs")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderDTO createOrder(@RequestBody CreateOrderDTO dto,
                                @RequestHeader("Authorization") String authToken) {
        return orderService.createOrder(dto, authToken);
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/user/{userId}")
    public List<OrderDTO> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }
}