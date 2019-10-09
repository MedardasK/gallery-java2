package com.service;

import com.entity.Image;
import com.payload.ImageUpdate;
import com.payload.ImageUpload;
import com.payload.ThumbnailDetails;

import java.util.List;

public interface IImageService {

    Image saveImage(ImageUpload imageUpload);

    byte[] getImage(Long imageId);

    Image getImageDetails(Long imageId);

    List<ThumbnailDetails> getAllImages();

    String deleteImage(Long fileId);

    Image updateImage(Long id, ImageUpdate imageUpdate);

}
