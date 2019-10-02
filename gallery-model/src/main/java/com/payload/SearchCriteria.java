package com.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchCriteria {
    String tagsNames;
    String categoriesIds;
    String searchString;
}
