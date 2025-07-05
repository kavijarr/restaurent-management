package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Order;
import org.example.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/active/{tableId}")
    public ResponseEntity<Order> getActiveOrder(@PathVariable Long tableId){
        Order order = orderService.getActiveOrder(tableId);
        if (order !=null) return ResponseEntity.ok(order);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/start/{tableId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long tableId){
        return ResponseEntity.ok(orderService.createOrder(tableId));
    }
}
