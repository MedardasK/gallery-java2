package com.controller;

import com.entity.Category;
import com.service.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    public ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> categoryList(){
        return categoryService.findAllCategories();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody String name) {
        if (name != null && !name.equals("")) {
            return ResponseEntity.ok(categoryService.saveCategory(name));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id){
        if (id > 0) {
            return ResponseEntity.ok(categoryService.deleteCategory(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
