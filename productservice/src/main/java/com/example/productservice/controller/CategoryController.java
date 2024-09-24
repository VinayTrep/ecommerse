package com.example.productservice.controller;

import com.example.productservice.dtos.CategoryResponseDto;
import com.example.productservice.dtos.CreateCategoryRequestDto;
import com.example.productservice.dtos.UpdateCategoryRequestDto;
import com.example.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    @Qualifier("CategoryService")
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CreateCategoryRequestDto requestDto){
        return ResponseEntity.status(201).body(categoryService.CreateCategory(requestDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        return ResponseEntity.status(200).body(categoryService.GetAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable UUID id){
        return ResponseEntity.status(200).body(categoryService.GetCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable UUID id, @RequestBody UpdateCategoryRequestDto requestDto){
        return ResponseEntity.status(200).body(categoryService.UpdateCategory(requestDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete Category Successfully");
    }


}
