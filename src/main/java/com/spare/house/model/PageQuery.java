package com.spare.house.model;

import lombok.Data;

/**
 * Created by dada on 2017/7/16.
 */
@Data
public class PageQuery {

    public PageQuery() {
        this.page = 1;
        this.perPageCount = 100;
    }

    private int page;

    private int perPageCount;

    public int getSkip() {
        if(page == 0) {
            return 0;
        }
        return (page - 1) * perPageCount;
    }

}
