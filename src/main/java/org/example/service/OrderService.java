package org.example.service;

import org.example.dto.OrderDTO;
import org.example.entity.DiningTable;
import org.example.entity.Food;
import org.example.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDTO createOrderIfNotActive(String qrCode);
    OrderDTO addFoodToOrder(String qrCode, Long foodId, int qty);
    void completeOrder(Long orderId);
    OrderDTO getOrder(Long orderId);
}
