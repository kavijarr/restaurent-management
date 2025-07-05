package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.OrderDTO;
import org.example.entity.Order;
import org.example.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/start")
    public OrderDTO startOrder(@RequestParam String qrCode) {
        return orderService.createOrderIfNotActive(qrCode);
    }

    @PostMapping("/add")
    public OrderDTO addFood(@RequestParam String qrCode,
                            @RequestParam Long foodId,
                            @RequestParam int qty) {
        return orderService.addFoodToOrder(qrCode, foodId, qty);
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestParam Long orderId) {
        orderService.completeOrder(orderId);
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }
}
