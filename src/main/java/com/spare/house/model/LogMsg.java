package com.spare.house.model;

import lombok.Data;
import sun.util.resources.cldr.guz.LocaleNames_guz;

/**
 * Created by dada on 2017/8/2.
 */
@Data
public class LogMsg {

    private String msg;

    private Integer code;

    private Long timestamp;

    public LogMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.timestamp = System.currentTimeMillis();
    }

}
