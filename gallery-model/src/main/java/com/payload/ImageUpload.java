package com.payload;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class ImageUpload {
        private MultipartFile file;
        private String description;
        private String categories;
        private String tags;
}
