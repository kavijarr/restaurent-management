package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.Food;
import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.example.repository.FoodRepository;
import org.example.repository.OrderItemRepository;
import org.example.repository.OrderRepository;
import org.example.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final OrderItemRepository orderItemRepository;
    @Override
    public OrderItem addFoodToOrder(Long orderId, Long foodId, int quantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .food(food)
                .quantity(quantity)
                .build();

        order.getItems().add(orderItem);
        orderRepository.save(order);
        return orderItemRepository.save(orderItem);
    }
}
