package com.DAO;

import com.entity.ImageFull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageFullRep extends JpaRepository<ImageFull, Long> {
}
