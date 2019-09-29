package com.DAO;

import com.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRep extends JpaRepository<Image, Long> {

}