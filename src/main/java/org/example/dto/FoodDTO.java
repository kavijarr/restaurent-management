package org.example.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDTO {
    private Long id;
    private String name;
    private double price;
    private String category;
    private String imageUrl;
}
