package com.controller;


import com.entity.Category;
import com.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    public ICategoryService categoryService;

    @GetMapping()
    public List<Category> categoryList(){
        return categoryService.findAllCategories();
    }

    @PostMapping("/create")
    public Category saveCategory(@RequestParam String category) {
        System.out.println("name");
        String cat = "asd";
        return categoryService.saveCategory(cat);
    }

//@PostMapping("/create")
//    public Category saveCategory(@RequestBody Category category){
//        System.out.println("test");
//        return categoryService.saveCategory(category);
//    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
    }

}
