package com.service;

import com.entity.Image;
import com.payload.ImageUpdate;
import com.payload.ImageUpload;

import java.util.List;

public interface IImageService {

    Image saveImage(ImageUpload imageUpload);

    Image getImage(Long imageId);

    List<Image> getAllImages();

    String deleteImage(Long fileId);

    Image updateImage(Long id, ImageUpdate imageUpdate);

}
