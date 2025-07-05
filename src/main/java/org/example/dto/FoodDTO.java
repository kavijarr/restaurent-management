package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoodDTO {
    private Long id;
    private String name;
    private double price;
    private String category;
    private String imageUrl;
}
