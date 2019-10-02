package com.service;


import com.entity.Image;
import com.payload.SearchCriteria;

import java.util.List;

public interface IImageSpecification {

    List<Image> searchImages(SearchCriteria searchCriteria);
}
