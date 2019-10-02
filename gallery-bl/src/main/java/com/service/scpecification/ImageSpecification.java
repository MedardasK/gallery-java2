package com.service.scpecification;


import com.DAO.IImageSearchRep;
import com.entity.Category;
import com.entity.Image;
import com.entity.Tag;
import com.payload.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageSpecification implements IImageSpecification {

    @Autowired
    private IImageSearchRep searchRep;

    @Transactional
    public List<Image> searchImages(SearchCriteria searchCriteria) {
        List<Image> imagesList = searchRep.findAll(ImageSearchSpecification.findByCriteria(searchCriteria));
        return imagesList;
    }

    private static class ImageSearchSpecification {
        private static Specification<Image> findByCriteria(SearchCriteria searchCriteria) {
            return (Specification<Image>) (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                List<String> tagsArray;
                query.distinct(true);
                Predicate searchPredicate = null;

                if (!searchCriteria.getCategoriesIds().isEmpty()) {
                    List<Long> categoriesIdsList = Arrays.stream(searchCriteria.getCategoriesIds().split(","))
                            .map(Long::valueOf).collect(Collectors.toList());
                    Join<Image, Category> categories = root.join("categories", JoinType.LEFT);
                    predicates.add(categories.in(categoriesIdsList));
                }

                if (!searchCriteria.getTagsNames().isEmpty()) {
                    tagsArray = Arrays.asList(searchCriteria.getTagsNames().split(","));
                    Join<Image, Tag> tags = root.join("tags", JoinType.LEFT);
                    predicates.add(cb.equal(tags.get("name"), tagsArray.get(0)));
                    for (int i = 1; i < tagsArray.size(); i++) {
                        predicates.add(cb.equal(tags.get("name"), tagsArray.get(i)));
                    }
                }

                if (!searchCriteria.getSearchString().isEmpty()) {
                    predicates.add(cb.like(root.get("description"), "%" + searchCriteria.getSearchString() + "%"));
                    predicates.add(cb.like(root.get("name"), "%" + searchCriteria.getSearchString() + "%"));
                }

                return cb.or(predicates.toArray(new Predicate[predicates.size()]));
            };
        }
    }

}
