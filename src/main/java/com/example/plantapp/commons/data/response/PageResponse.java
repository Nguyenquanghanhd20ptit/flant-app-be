package com.example.plantapp.commons.data.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageResponse <T>{
    private List<T> items;
    private Long total;
}
