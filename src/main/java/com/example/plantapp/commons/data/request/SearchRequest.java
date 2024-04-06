package com.example.plantapp.commons.data.request;


import com.example.plantapp.commons.data.model.Filter;
import com.example.plantapp.commons.data.model.paging.PageableCustom;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SearchRequest {
    private String keyword;
    private List<Filter> filters;
    private PageableCustom pageable;
    List<String> searchColumns;
}
