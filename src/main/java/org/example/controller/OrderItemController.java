package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.OrderItem;
import org.example.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderitem")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    public ResponseEntity<OrderItem> addFoodToOrder(
            @RequestParam Long orderId,
            @RequestParam Long foodId,
            @RequestParam int quantity){
        return ResponseEntity.ok(orderItemService.addFoodToOrder(orderId, foodId, quantity));
    }
}