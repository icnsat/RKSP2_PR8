package org.example.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.DTO.CreateOrderDTO;
import org.example.DTO.OrderDTO;
import org.example.DTO.ProductDTO;
import org.example.DTO.UserDTO;
import org.example.Feign.ProductServiceClient;
import org.example.Feign.UserServiceClient;
import org.example.Model.Order;
import org.example.Model.OrderStatus;
import org.example.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserServiceClient userServiceClient;
    private final ProductServiceClient productServiceClient;

    public OrderDTO createOrder(CreateOrderDTO dto, String authToken) {
        // 1. Валидируем пользователя
        if (!userServiceClient.validateToken(authToken)) {
            throw new RuntimeException("Invalid user token");
        }

        // 2. Проверяем существование пользователя
        UserDTO user = userServiceClient.getUser(dto.getUserId());

        // 3. Получаем информацию о товаре
        ProductDTO product = productServiceClient.getProduct(dto.getProductId());

        // 4. Проверяем наличие
        if (product.getStockQuantity() < dto.getQuantity()) {
            throw new RuntimeException("Not enough stock for product: " + product.getName());
        }

        // 5. Резервируем товар
        productServiceClient.updateStock(dto.getProductId(), -dto.getQuantity());

        // 6. Считаем общую стоимость
        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity()));

        // 7. Создаем заказ
        Order order = Order.builder()
                .userId(dto.getUserId())
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .totalPrice(totalPrice)
                .status(OrderStatus.PENDING)
                .build();

        Order savedOrder = orderRepository.save(order);
        return mapToDTO(savedOrder, user, product);
    }

    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Получаем дополнительные данные
        UserDTO user = userServiceClient.getUser(order.getUserId());
        ProductDTO product = productServiceClient.getProduct(order.getProductId());

        return mapToDTO(order, user, product);
    }

    public List<OrderDTO> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);

        return orders.stream()
                .map(order -> {
                    UserDTO user = userServiceClient.getUser(order.getUserId());
                    ProductDTO product = productServiceClient.getProduct(order.getProductId());
                    return mapToDTO(order, user, product);
                })
                .collect(Collectors.toList());
    }

    private OrderDTO mapToDTO(Order order, UserDTO user, ProductDTO product) {
        return OrderDTO.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .userName(user.getUsername()) // из User Service
                .productId(order.getProductId())
                .productName(product.getName()) // из Product Service
                .productPrice(product.getPrice())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}