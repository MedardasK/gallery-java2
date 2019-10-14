package com.service;


import com.payload.ThumbnailDetails;

import java.util.List;

public interface IImageSpecification {

    List<ThumbnailDetails> searchImages(String categoriesIds, String tagsNames, String searchString);

}
