package com.payload;

import com.entity.Category;
import com.entity.Tag;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class ImageUpdate {

    private Long id;
    private String name;
    private String description;
    private String date = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
    private Set<Tag> tags = new HashSet<>();
    private Set<Category> categories = new HashSet<>();
}
