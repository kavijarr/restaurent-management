package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.OrderDTO;
import org.example.dto.OrderItemDTO;
import org.example.entity.DiningTable;
import org.example.entity.Food;
import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.example.factory.OrderFactory;
import org.example.repository.DiningTableRepository;
import org.example.repository.FoodRepository;
import org.example.repository.OrderItemRepository;
import org.example.repository.OrderRepository;
import org.example.service.DiningTableService;
import org.example.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final DiningTableRepository diningTableRepository;
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final OrderItemRepository orderItemRepository;
    @Override
    public OrderDTO createOrderIfNotActive(String qrCode) {
        DiningTable table = diningTableRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Invalid QR Code"));

        Order order = orderRepository.findByTableAndActiveTrue(table)
                .orElseGet(() -> {
                    Order newOrder = Order.builder()
                            .table(table)
                            .active(true)
                            .delivery(false)
                            .createdAt(LocalDateTime.now())
                            .totalPrice(0.0)
                            .build();
                    return orderRepository.save(newOrder);
                });

        return mapToDTO(order);
    }

    @Override
    public OrderDTO addFoodToOrder(String qrCode, Long foodId, int qty) {
        DiningTable table = diningTableRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Invalid QR Code"));

        Order order = orderRepository.findByTableAndActiveTrue(table)
                .orElseThrow(() -> new RuntimeException("No active order for table"));

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Invalid food ID"));

        OrderItem item = OrderItem.builder()
                .order(order)
                .food(food)
                .quantity(qty)
                .build();

        order.getItems().add(item);
        order.setTotalPrice(order.getTotalPrice() + food.getPrice() * qty);
        orderRepository.save(order);

        return mapToDTO(order);
    }

    @Override
    public void completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setActive(false);
        orderRepository.save(order);
    }

    @Override
    public OrderDTO getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isOrderStillActive(Long orderId) {
        return orderRepository.findById(orderId)
                .map(Order::isActive)
                .orElse(false);
    }

//    private OrderDTO mapToDTO(Order order) {
//        return OrderDTO.builder()
//                .id(order.getId())
//                .active(order.isActive())
//                .delivery(order.isDelivery())
//                .totalPrice(order.getTotalPrice())
//                .createdAt(order.getCreatedAt())
//                .tableId(order.getTable().getId())
//                .tableName(order.getTable().getName())
//                .items(order.getItems()
//                        .stream()
//                        .map(this::mapOrderItemToDTO)
//                        .collect(Collectors.toList()))
//                .build();
//    }

    private OrderDTO mapToDTO(Order order) {
        List<OrderItem> items = order.getItems();
        if (items == null) {
            items = new ArrayList<>();
        }

        return OrderDTO.builder()
                .id(order.getId())
                .active(order.isActive())
                .delivery(order.isDelivery())
                .totalPrice(order.getTotalPrice())
                .createdAt(order.getCreatedAt())
                .tableId(order.getTable().getId())
                .tableName(order.getTable().getName())
                .items(items.stream()
                        .map(this::mapOrderItemToDTO)
                        .collect(Collectors.toList()))
                .build();
    }


    private OrderItemDTO mapOrderItemToDTO(OrderItem item) {
        return OrderItemDTO.builder()
                .foodId(item.getFood().getId())
                .foodName(item.getFood().getName())
                .quantity(item.getQuantity())
                .price(item.getFood().getPrice())
                .build();
    }
}
