package org.example.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private boolean active;
    private boolean delivery;
    private double totalPrice;
    private LocalDateTime createdAt;
    private Long tableId;
    private String tableName;
    private List<OrderItemDTO> items;
}
