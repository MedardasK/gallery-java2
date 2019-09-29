package com.DAO;

import com.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ICategoryRep extends JpaRepository<Category, Long> {

    Category findByName(String name);

    List<Category> findAll();

    Set<Category> findByIdIn(List<Long> categoriesIdsList);
}
