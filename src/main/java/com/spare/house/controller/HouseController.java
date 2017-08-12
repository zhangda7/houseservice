package com.spare.house.controller;

import com.alibaba.fastjson.JSON;
import com.spare.house.dao.HouseMongoDao;
import com.spare.house.model.House;
import com.spare.house.model.PageQuery;
import com.spare.house.model.RestfulPage;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dada on 2017/7/29.
 */
@RestController
public class HouseController {

    @Autowired
    HouseMongoDao houseMongoDao;

    @GetMapping("/rest/v1/house")
    public RestfulPage list(@RequestParam(value = "estateName", required = false) String estateName,
                                        @RequestParam(value = "houseId", required = false) String houseId,
                                        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                        @RequestParam(value = "pageCount", required = false, defaultValue = "50") Integer pageCount) {
        RestfulPage restfulPage = new RestfulPage();
        if(estateName == null && houseId == null) {
            restfulPage.setCode(301);
            restfulPage.setMsg("Param estateName and houseId can not both null");
            return restfulPage;
        }

        PageQuery pageQuery = new PageQuery();
        pageQuery.setPage(page);
        pageQuery.setPerPageCount(pageCount);

        House where = new House();
        where.setEstateName(estateName);
        where.setHouseId(houseId);

        List<House> houseList = houseMongoDao.list(where, pageQuery);
        restfulPage.setCode(200);
        restfulPage.setSize(houseList.size());
        restfulPage.setData(JSON.toJSONString(houseList));

        return restfulPage;
    }

}
