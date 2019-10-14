package com.service.implementations;


import com.DAO.IImageSearchRep;
import com.entity.Category;
import com.entity.Image;
import com.entity.Tag;
import com.payload.ThumbnailDetails;
import com.service.IImageSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageSpecification implements IImageSpecification {

    private IImageSearchRep searchRep;

    public ImageSpecification(IImageSearchRep searchRep) {
        this.searchRep = searchRep;
    }

    @Transactional
    public List<ThumbnailDetails> searchImages(String categoriesIds, String tagsNames, String searchString) {
        List<Image> imagesList = searchRep.findAll(ImageSearchSpecification.findByCriteria(categoriesIds, tagsNames, searchString));

        List<ThumbnailDetails> thumbnailDetails = imagesList.stream()
                .map(image -> new ThumbnailDetails(image.getId(), image.getData(),
                        image.getName(), image.getDescription(), image.getDate(),
                        image.getCategories(), image.getTags())).collect(Collectors.toList());
        return thumbnailDetails;
    }

}
