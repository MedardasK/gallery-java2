package com.service.implementations;

import com.DAO.ICategoryRep;
import com.DAO.IImageRep;
import com.DAO.ITagRep;
import com.entity.Image;
import com.entity.ImageFull;
import com.exceptions.NotFoundException;
import com.payload.ImageUpdate;
import com.payload.ImageUpload;
import com.payload.ResizedImage;
import com.service.IImageService;
import com.util.ImageResizeUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImageService implements IImageService {

    private IImageRep imageRep;
    private ITagRep tagRep;
    private ICategoryRep categoryRep;

    public ImageService(IImageRep imageRep, ITagRep tagRep, ICategoryRep categoryRep) {
        this.imageRep = imageRep;
        this.tagRep = tagRep;
        this.categoryRep = categoryRep;
    }

    public Image saveImage(ImageUpload imageUpload) {
        MultipartFile file = imageUpload.getFile();
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image();

        try {
            if (imageName.contains("..")) {
                throw new NotFoundException("Filename contains invalid path sequence " + imageName);
            }
            String imageString = imageName.substring(0, imageName.lastIndexOf("."));
            ResizedImage resizedImage = ImageResizeUtil.resize(file.getBytes());
            ImageFull imageFull = new ImageFull();
            imageFull.setData(file.getBytes());

            image.setName(imageString);
            image.setType(file.getContentType());
            image.setSize(file.getSize());
            image.setData(resizedImage.getData());
            image.setDescription(imageUpload.getDescription());
            image.setHeight(resizedImage.getHeight());
            image.setWidth(resizedImage.getWidth());
            image.setImageFull(imageFull);
            image.setCategories(categoryRep.findByIdIn(extractIds(imageUpload.getCategories())));
            image.setTags(tagRep.findByIdIn(extractIds(imageUpload.getTags())));

            return imageRep.save(image);
        } catch (Exception exception) {
            return null;
        }
    }

    public Image getImage(Long imageId) {
        Optional<Image> imageOptional = imageRep.findById(imageId);
        if (imageOptional.isPresent()) {
            return imageOptional.get();
        } else {
            return null;
        }
    }

    public List<Image> getAllImages() {
        return imageRep.findAll();
    }

    public String deleteImage(Long imageId) {

        try {
            imageRep.deleteById(imageId);
            return "\"Success\"";
        } catch (Exception err) {
            return err.toString();
        }
    }

    public Image updateImage(Long id, ImageUpdate imageUpdate) {
        Optional<Image> optionalImage = imageRep.findById(id);
        Image image;

        if (optionalImage != null) {
            image = optionalImage.get();
            image.setCategories(categoryRep.findByIdIn(extractIds(imageUpdate.getCategories())));
            image.setTags(tagRep.findByIdIn(extractIds(imageUpdate.getTags())));
            image.setName(imageUpdate.getName());
            image.setDescription(imageUpdate.getDescription());
            image.setDate(imageUpdate.getDate());
            return imageRep.save(image);
        } else {
            return null;
        }
    }

    private List<Long> extractIds(String value) {
        if (value.isEmpty() || value.replace("{", "").replace("}", "").isEmpty()) {
            return Collections.emptyList();
        } else {
            return Arrays.stream(value.replace("{", "").replace("}", "")
                    .split(",")).map(Long::valueOf).collect(Collectors.toList());
        }
    }

}