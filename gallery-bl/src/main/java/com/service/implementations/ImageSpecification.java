package com.service.implementations;


import com.DAO.IImageSearchRep;
import com.entity.Category;
import com.entity.Image;
import com.entity.Tag;
import com.payload.SearchCriteria;
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
    public List<Image> searchImages(SearchCriteria searchCriteria) {
        List<Image> imagesList = searchRep.findAll(ImageSearchSpecification.findByCriteria(searchCriteria));
        return imagesList;
    }

    private static class ImageSearchSpecification {
        private static Specification<Image> findByCriteria(SearchCriteria searchCriteria) {
            return (Specification<Image>) (root, query, cb) -> {
                List<String> tagsArray;
                query.distinct(true);

                Predicate categoriesSearchPredicate = null;
                Predicate predicates = null;
                Predicate searchPredicate = null;
                Predicate tagsSearchPredicate = null;

                if (!searchCriteria.getCategoriesIds().isEmpty()) {
                    List<Long> categoriesIdsList = Arrays.stream(searchCriteria.getCategoriesIds().split(","))
                            .map(Long::valueOf).collect(Collectors.toList());
                    Join<Image, Category> categories = root.join("categories", JoinType.LEFT);
                    categoriesSearchPredicate = categories.in(categoriesIdsList);
                }

                if (!searchCriteria.getTagsNames().isEmpty()) {
                    tagsArray = Arrays.asList(searchCriteria.getTagsNames().split(","));
                    Join<Image, Tag> tags = root.join("tags", JoinType.LEFT);
                    tagsSearchPredicate = cb.equal(tags.get("name"), tagsArray.get(0));
                    for (int i = 1; i < tagsArray.size(); i++) {
                        Predicate currentPredicate = cb.equal(tags.get("name"), tagsArray.get(i));
                        tagsSearchPredicate = cb.or(tagsSearchPredicate, currentPredicate);
                    }
                }

                if (!searchCriteria.getSearchString().isEmpty()) {
                    searchPredicate = cb.or(cb.like(root.get("description"), "%" + searchCriteria.getSearchString() + "%"),
                            cb.like(root.get("name"), "%" + searchCriteria.getSearchString() + "%"));
                }

                if (categoriesSearchPredicate != null) {
                    predicates = categoriesSearchPredicate;
                }

                if (tagsSearchPredicate != null) {
                    if (predicates != null) {
                        predicates = cb.and(predicates, tagsSearchPredicate);
                    } else {
                        predicates = tagsSearchPredicate;
                    }
                }

                if (searchPredicate != null) {
                    if (predicates != null) {
                        predicates = cb.and(predicates, searchPredicate);
                    } else {
                        predicates = searchPredicate;
                    }
                }
                return predicates;
            };
        }
    }

}