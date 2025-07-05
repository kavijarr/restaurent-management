package org.example.repository;

import org.example.entity.DiningTable;
import org.example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findByTableAndActiveTrue(DiningTable table);
}
