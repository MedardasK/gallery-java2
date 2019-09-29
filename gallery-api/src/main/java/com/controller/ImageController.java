package com.controller;

import com.entity.Image;
import com.payload.ImageUpdate;
import com.payload.ImageUpload;
import com.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Image> getImageById(@PathVariable(value = "id") Long id) {
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
    public ResponseEntity<List<Image>> getAllImagesBySearch(@RequestParam String searchParams) {
        if (!searchParams.isEmpty()) {
            return ResponseEntity.ok(imageService.getAllImagesBySearch(searchParams));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}