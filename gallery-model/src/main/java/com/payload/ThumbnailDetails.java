package com.payload;

import com.entity.Category;
import com.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThumbnailDetails {
    Long id;
    byte[] data;
    String name;
    String description;
    String date;
    Set<Category> categories;
    Set<Tag> tags;
}
