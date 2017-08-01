package com.spare.house.controller;

import com.spare.house.model.PageQuery;
import com.spare.house.model.RestfulPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dada on 2017/7/29.
 */
@RestController
public class HouseController {

    @GetMapping("/rest/v1/house")
    public RestfulPage list(@RequestParam(value = "estateName", required = false) String estateName,
                                        @RequestParam(value = "houseId", required = false) String houseId,
                                        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                        @RequestParam(value = "pageCount", required = false, defaultValue = "50") Integer pageCount) {
        if(estateName == null || houseId == null) {
            RestfulPage restfulPage = new RestfulPage();
            restfulPage.setCode(301);
            restfulPage.setMsg("Param estateName and houseId can not both null");
            return restfulPage;
        }

        PageQuery pageQuery = new PageQuery();
        pageQuery.setPage(page);
        pageQuery.setPerPageCount(pageCount);

        return null;
    }

}
