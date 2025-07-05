package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.FoodDTO;
import org.example.entity.Food;
import org.example.repository.FoodRepository;
import org.example.service.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    @Override
    public FoodDTO createFood(FoodDTO dto) {
        Food food = Food.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .imageUrl(dto.getImageUrl())
                .build();
        Food saved = foodRepository.save(food);
        return mapToDTO(saved);
    }

    @Override
    public List<FoodDTO> getAllFoods() {
        return foodRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FoodDTO getFoodById(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));
        return mapToDTO(food);
    }

    @Override
    public FoodDTO updateFood(Long id, FoodDTO dto) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));
        food.setName(dto.getName());
        food.setPrice(dto.getPrice());
        food.setCategory(dto.getCategory());
        food.setImageUrl(dto.getImageUrl());
        Food updated = foodRepository.save(food);
        return mapToDTO(updated);
    }

    @Override
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    private FoodDTO mapToDTO(Food food) {
        return FoodDTO.builder()
                .id(food.getId())
                .name(food.getName())
                .price(food.getPrice())
                .category(food.getCategory())
                .imageUrl(food.getImageUrl())
                .build();
    }
}
