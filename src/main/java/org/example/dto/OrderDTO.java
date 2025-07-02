package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {
    private Long id;
    private Long tableId;
    private Boolean active;
    private Boolean delivery;
    private Double totalPrice;
    private LocalDateTime orderTime;
    private List<Long> foodIds;
}
