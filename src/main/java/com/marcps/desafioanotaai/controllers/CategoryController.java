package com.marcps.desafioanotaai.controllers;

import com.marcps.desafioanotaai.domain.category.Category;
import com.marcps.desafioanotaai.domain.category.CategoryDTO;
import com.marcps.desafioanotaai.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Category> createCategory(@Validated @RequestBody CategoryDTO categoryData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryData));
    }

    @GetMapping()
    public ResponseEntity<List<Category>> listarCategorias() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.listarCategorias());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable String id, @RequestBody CategoryDTO categoryData) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id, categoryData));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategory(id));
    }
}
