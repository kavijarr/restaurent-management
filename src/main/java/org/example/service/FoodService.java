package org.example.service;

import org.example.dto.FoodDTO;
import org.example.entity.Food;

import java.util.List;
import java.util.Optional;

public interface FoodService {
    FoodDTO createFood(FoodDTO dto);
    List<FoodDTO> getAllFoods();
    FoodDTO getFoodById(Long id);
    FoodDTO updateFood(Long id, FoodDTO dto);
    void deleteFood(Long id);
}