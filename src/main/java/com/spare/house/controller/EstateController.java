package com.spare.house.controller;

import com.alibaba.fastjson.JSON;
import com.spare.house.model.Estate;
import com.spare.house.model.PageQuery;
import com.spare.house.model.RestfulPage;
import com.spare.house.service.EstateService;
import com.spare.house.util.HouseServiceConstans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dada on 2017/7/16.
 */
@RestController
public class EstateController {

    @Autowired
    EstateService estateService;

    @GetMapping("/rest/v1/estate/listByDistrict")
    public RestfulPage listByDistrict(@RequestParam("district") String district,
                       @RequestParam("startPage") Integer startPage,
                       @RequestParam("pageCount") Integer pageCount) {

        if(district == null || startPage == null || pageCount == null) {
            RestfulPage restfulPage = new RestfulPage();
            restfulPage.setCode(301);
            restfulPage.setMsg("Param error");
            return restfulPage;
        }

        Estate estate = new Estate();
        estate.setDistrict(district);
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPage(startPage);
        pageQuery.setPerPageCount(pageCount);

        return doList(estate, pageQuery);
    }

    private RestfulPage doList(Estate estate, PageQuery pageQuery) {
        RestfulPage restfulPage = new RestfulPage();
        List<Estate> estateList = estateService.list(estate, pageQuery);
        restfulPage.setCode(HouseServiceConstans.REST_CODE_OK);
        restfulPage.setData(JSON.toJSONString(estateList));
        return restfulPage;
    }

    @GetMapping("/rest/v1/estate/listByName")
    public RestfulPage listByName(String estateName) {
        if(estateName == null || estateName.isEmpty()) {
            RestfulPage restfulPage = new RestfulPage();
            restfulPage.setCode(301);
            restfulPage.setMsg("Param error");
            return restfulPage;
        }

        List<Estate> estateList = estateService.listByName(estateName);
        RestfulPage restfulPage = new RestfulPage();
        restfulPage.setCode(HouseServiceConstans.REST_CODE_OK);
        restfulPage.setData(JSON.toJSONString(estateList));
        return restfulPage;
    }

}
