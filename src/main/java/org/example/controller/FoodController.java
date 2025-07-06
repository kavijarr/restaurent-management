package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.FoodDTO;
import org.example.entity.Food;
import org.example.service.FileStorageService;
import org.example.service.FoodService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
    private final FileStorageService fileStorageService;

    @PostMapping
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

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FoodDTO> createFoodWithImage(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("category") String category,
            @RequestParam("image") MultipartFile imageFile) {

        String imageUrl = fileStorageService.storeFile(imageFile);

        FoodDTO dto = new FoodDTO();
        dto.setName(name);
        dto.setPrice(price);
        dto.setCategory(category);
        dto.setImageUrl(imageUrl);

        FoodDTO createdFood = foodService.createFood(dto);
        return ResponseEntity.ok(createdFood);
    }

}
