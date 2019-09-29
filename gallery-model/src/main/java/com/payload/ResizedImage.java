package com.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResizedImage {

    private byte[] data;
    private Integer width;
    private Integer height;

}
