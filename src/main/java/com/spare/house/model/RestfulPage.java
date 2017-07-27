package com.spare.house.model;

import lombok.Data;

/**
 * Created by dada on 2017/7/27.
 */
@Data
public class RestfulPage {

    /**
     * 200 -- OK
     * 3XX -- ERROR
     */
    private int code;

    /**
     * ERROR MSG
     */
    private String msg;

    /**
     * REAL DATA
     */
    private String data;

    /**
     * REAL DATA ARRAY SIZE
     */
    private int size;

}
