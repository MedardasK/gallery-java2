package com.controller;

import com.entity.Image;
import com.payload.ImageUpdate;
import com.payload.ImageUpload;
import com.payload.ThumbnailDetails;
import com.service.IImageService;
import com.service.IImageSpecification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("images")
public class ImageController {

    private IImageService imageService;
    private IImageSpecification imageSpecification;

    public ImageController(IImageService imageService, IImageSpecification imageSpecification) {
        this.imageService = imageService;
        this.imageSpecification = imageSpecification;
    }

    @GetMapping()
    public List<ThumbnailDetails> getAllImages() {
        return imageService.getAllImages();
    }

    @GetMapping("/imageDetails/{id}")
    public ResponseEntity<Image> getImageDetailsById(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            return ResponseEntity.ok(imageService.getImageDetails(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody ResponseEntity<byte[]> getImageById(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            return ResponseEntity.ok(imageService.getImage(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/upload")
    public ResponseEntity<Image> uploadFile(@ModelAttribute ImageUpload imageUpload) {
        if (imageUpload.getFile() != null && imageUpload.getCategories() != null && imageUpload.getTags() != null
        && imageUpload.getDescription() != null){
            return ResponseEntity.ok(imageService.saveImage(imageUpload));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable(value = "id") Long id,
                                             @ModelAttribute ImageUpdate imageUpdate) {
        if (id > 0 && imageUpdate.getCategories() != null && imageUpdate.getTags() != null
                && imageUpdate.getDescription() != null && imageUpdate.getName() != null){
            return ResponseEntity.ok(imageService.updateImage(id, imageUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            return ResponseEntity.ok(imageService.deleteImage(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/")
    public ResponseEntity<List<ThumbnailDetails>> getAllImagesBySearch(@RequestParam String categoriesIds,
                                                            @RequestParam String tagsNames,
                                                            @RequestParam String searchString) {
        if (!searchString.isEmpty() || !categoriesIds.isEmpty() || !tagsNames.isEmpty()) {
            return ResponseEntity.ok(imageSpecification.searchImages(categoriesIds, tagsNames, searchString));
        } else {
            return null;
        }
    }

}