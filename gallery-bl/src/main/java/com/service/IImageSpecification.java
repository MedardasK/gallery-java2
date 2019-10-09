package com.service;


import com.entity.Image;
import com.payload.SearchCriteria;
import com.payload.ThumbnailDetails;

import java.util.List;

public interface IImageSpecification {

    List<ThumbnailDetails> searchImages(SearchCriteria searchCriteria);
}
