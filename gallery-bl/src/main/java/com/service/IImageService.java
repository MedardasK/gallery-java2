package com.service;

import com.entity.Image;
import com.payload.ImageUpdate;
import com.payload.ImageUpload;

import java.util.List;
import java.util.Set;

public interface IImageService {

    Image saveImage(ImageUpload imageUpload);

    Image getImage(Long imageId);

    List<Image> getAllImages();

    void deleteImage(Long fileId);

    List<Image> getAllImagesBySearch(String searchString, Set<Long> tagsIds, Set<Long> categoriesIds);

    Image updateImage(ImageUpdate imageUpdate);
}
