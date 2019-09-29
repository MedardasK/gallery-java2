package com.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class ImageUpdate {

    private String name;
    private String description;
    private String date = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
    private String tags;
    private String categories;
}
