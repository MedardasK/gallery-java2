package com.service;

import com.entity.Image;
import com.payload.ImageUpdate;
import com.payload.ImageUpload;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IImageService {

    Image saveImage(ImageUpload imageUpload);

    Image getImage(Long imageId);

    List<Image> getAllImages();

    String deleteImage(Long fileId);

    List<Image> getAllImagesBySearch(String searchString, String categoriesIds, String tagsNames);

    Image updateImage(Long id, ImageUpdate imageUpdate);

}
