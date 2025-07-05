package org.example.service;

import org.example.entity.OrderItem;

public interface OrderItemService {
    OrderItem addFoodToOrder(Long orderId, Long foodId, int quantity);
}
