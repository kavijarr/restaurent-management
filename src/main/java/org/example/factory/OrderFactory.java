package org.example.factory;

import org.example.entity.DiningTable;
import org.example.entity.Food;
import org.example.entity.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderFactory {
    public static Order createDineInOrder(DiningTable table, List<Food> foods, double totalPrice) {
        return Order.builder()
                .table(table)
                //.foods(foods)
                .totalPrice(totalPrice)
                .createdAt(LocalDateTime.now())
                .active(true)
                .build();
    }
}
