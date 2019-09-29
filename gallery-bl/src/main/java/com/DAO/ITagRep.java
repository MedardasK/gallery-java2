package com.DAO;

import com.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ITagRep extends JpaRepository<Tag, Long> {

    Tag findByName(String name);

    List<Tag> findAll();

    Set<Tag> findByIdIn(List<Long> tagsIdsList);
}