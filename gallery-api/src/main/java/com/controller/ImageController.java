package com.controller;


import com.entity.Image;
import com.payload.ImageUpdate;
import com.payload.ImageUpload;
import com.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("images")
public class ImageController {

    @Autowired
    private IImageService imageService;

    @GetMapping()
    public List<Image> getAllImages() {
        return imageService.getAllImages();
    }

    @GetMapping("/image/{id}")
    public Image getImageById(@PathVariable(value = "id") Long fileId) {
        return imageService.getImage(fileId);
    }

    @PostMapping("/upload")
    public Image uploadFile(@ModelAttribute ImageUpload imageUpload) {
        return imageService.saveImage(imageUpload);
    }


//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable(value = "id") Long Id) {
        imageService.deleteImage(Id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/{imageUpdate}")
    public Image updateImage(@ModelAttribute ImageUpdate imageUpdate) {
        return imageService.updateImage(imageUpdate);
    }

    @GetMapping("/search/{searchString}{tagsIds}{categoriesIds}")
    public List<Image> getAllImagesBySearch(@PathVariable(value = "searchString") String searchString,
                                            @PathVariable(value = "tagsIds") Set<Long> tagsIds,
                                            @PathVariable(value = "categoriesIds") Set<Long> categoriesIds) {
        return imageService.getAllImagesBySearch(searchString, tagsIds, categoriesIds);
    }
}