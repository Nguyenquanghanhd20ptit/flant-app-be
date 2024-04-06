package com.example.plantapp.commons.data.model.paging;

import lombok.Data;

import java.util.List;

@Data
public class PageableCustom {
    public static final Integer DEFAULT_PAGE = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer MAXIMUM_PAGE_SIZE = 500;

    private int page;
    private int pageSize;
    private Integer offset;
    private long total;
    private List<OrderCustom> sort;
    private Boolean loadMoreAble;

    public PageableCustom() {
        this.page = DEFAULT_PAGE;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.offset = Math.max((page - 1) * pageSize, 0);
        this.total = 0L;
    }

    public PageableCustom(int page, int pageSize) {
        this.page = page > 0 ? page : DEFAULT_PAGE;
        this.pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
        this.offset = Math.max((page - 1) * pageSize, 0);
        this.total = 0L;
    }

    public Integer getOffset() {
        if (offset == null || offset <= 0) {
            return Math.max((page - 1) * pageSize, 0);
        }
        return offset;
    }
}
