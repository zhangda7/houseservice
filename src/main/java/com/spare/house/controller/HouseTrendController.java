package com.spare.house.controller;

import com.alibaba.fastjson.JSON;
import com.spare.house.model.House;
import com.spare.house.model.HouseTrend;
import com.spare.house.model.PageQuery;
import com.spare.house.model.RestfulPage;
import com.spare.house.service.HouseTrendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dada on 2017/7/25.
 */
@RestController
public class HouseTrendController {

    private static Logger logger = LoggerFactory.getLogger(HouseTrendController.class);

    @Autowired
    HouseTrendService houseTrendService;

    @GetMapping("/rest/v1/house/trend")
    public RestfulPage query(@RequestParam("houseId") String houseId) {
        logger.info("Receive request {}", houseId);

        RestfulPage restfulPage = new RestfulPage();
        if(houseId == null) {
            restfulPage.setCode(301);
            restfulPage.setMsg("Param estateName and houseId can not both null");
            return restfulPage;
        }

        HouseTrend where = new HouseTrend();
        where.setHouseId(houseId);
        List<HouseTrend> houseTrends = houseTrendService.list(where, new PageQuery());
        restfulPage.setCode(200);
        restfulPage.setSize(houseTrends.size());
        restfulPage.setData(JSON.toJSONString(houseTrends));

        return restfulPage;
    }

    @GetMapping("/rest/v1/house/trendDetail")
    public RestfulPage queryWithHouseDetail(@RequestParam("houseId") String houseId) {
        logger.info("Receive request {}", houseId);

        RestfulPage restfulPage = new RestfulPage();
        if(houseId == null) {
            restfulPage.setCode(301);
            restfulPage.setMsg("Param estateName and houseId can not both null");
            return restfulPage;
        }

        HouseTrend where = new HouseTrend();
        where.setHouseId(houseId);
        List<HouseTrend> houseTrends = houseTrendService.listWithDetail(where, new PageQuery());
        restfulPage.setCode(200);
        restfulPage.setSize(houseTrends.size());
        restfulPage.setData(JSON.toJSONString(houseTrends));

        return restfulPage;
    }

}
