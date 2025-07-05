package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.FoodDTO;
import org.example.entity.Food;
import org.example.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    public FoodDTO createFood(@RequestBody FoodDTO dto) {
        return foodService.createFood(dto);
    }

    @GetMapping
    public List<FoodDTO> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}")
    public FoodDTO getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }

    @PutMapping("/{id}")
    public FoodDTO updateFood(@PathVariable Long id, @RequestBody FoodDTO dto) {
        return foodService.updateFood(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
    }

}
