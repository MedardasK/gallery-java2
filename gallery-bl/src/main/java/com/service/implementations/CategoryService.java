package com.service.implementations;


import com.DAO.ICategoryRep;
import com.entity.Category;
import com.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRep categoryRep;

    public List<Category> findAllCategories() {
        return (List<Category>) categoryRep.findAll();
    }

    public Category saveCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRep.save(category);
    }
//    public Category saveCategory(Category category) {
//        return categoryRep.save(category);
//    }

    public void deleteCategory(Long tagId) {
        categoryRep.deleteById(tagId)
               /* .orElseThrow(() -> new MyFileNotFoundException("Category not found with id " + categoryId) {
                })*/
        ;
    }
}
