package com.spare.house.controller;

import com.alibaba.fastjson.JSON;
import com.spare.house.model.HouseTrend;
import com.spare.house.model.PageQuery;
import com.spare.house.service.HouseTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dada on 2017/7/25.
 */
@RestController
public class HouseTrendController {

    @Autowired
    HouseTrendService houseTrendService;

    @GetMapping("house/trend")
    public String query() {
        List<HouseTrend> houseTrends = houseTrendService.list(null, new PageQuery());
        return JSON.toJSONString(houseTrends);
    }

}
