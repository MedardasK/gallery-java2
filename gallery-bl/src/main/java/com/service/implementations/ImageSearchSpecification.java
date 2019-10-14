package com.service.implementations;

import com.entity.Category;
import com.entity.Image;
import com.entity.Tag;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ImageSearchSpecification {
    static Specification<Image> findByCriteria(String categoriesIds, String tagsNames, String searchString) {
        return (Specification<Image>) (root, query, cb) -> {
            List<String> tagsArray;
            query.distinct(true);

            Predicate categoriesSearchPredicate = null;
            Predicate predicates = null;
            Predicate searchPredicate = null;
            Predicate tagsSearchPredicate = null;

            if (!categoriesIds.isEmpty()) {
                List<Long> categoriesIdsList = Arrays.stream(categoriesIds.split(","))
                        .map(Long::valueOf).collect(Collectors.toList());
                Join<Image, Category> categories = root.join("categories", JoinType.LEFT);
                categoriesSearchPredicate = categories.in(categoriesIdsList);
            }

            if (!tagsNames.isEmpty()) {
                tagsArray = Arrays.asList(tagsNames.split(","));
                Join<Image, Tag> tags = root.join("tags", JoinType.LEFT);
                tagsSearchPredicate = cb.equal(tags.get("name"), tagsArray.get(0));
                for (int i = 1; i < tagsArray.size(); i++) {
                    Predicate currentPredicate = cb.equal(tags.get("name"), tagsArray.get(i));
                    tagsSearchPredicate = cb.or(tagsSearchPredicate, currentPredicate);
                }
            }

            if (!searchString.isEmpty()) {
                searchPredicate = cb.or(cb.like(root.get("description"), "%" + searchString + "%"),
                        cb.like(root.get("name"), "%" + searchString + "%"));
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
