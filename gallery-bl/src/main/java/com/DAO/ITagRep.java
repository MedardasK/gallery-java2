package com.DAO;


import com.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagRep extends JpaRepository<Tag, Long> {

}