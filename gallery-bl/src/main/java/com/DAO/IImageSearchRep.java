package com.DAO;

import com.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageSearchRep extends JpaRepository<Image, Long>, JpaSpecificationExecutor<Image> {
}
