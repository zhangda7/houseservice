package com.spare.house.service.impl;

import com.spare.house.dao.MongoDao;
import com.spare.house.model.HouseTrend;
import com.spare.house.model.PageQuery;
import com.spare.house.service.HouseTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dada on 2017/7/25.
 */
@Component
public class HouseTrendServiceImpl implements HouseTrendService {

    @Autowired
    MongoDao mongoDao;

    @Override
    public List<HouseTrend> list(String houseLink, PageQuery pageQuery) {
        return mongoDao.queryHouseTrend(houseLink, pageQuery);
    }

}
