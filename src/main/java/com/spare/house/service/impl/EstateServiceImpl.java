package com.spare.house.service.impl;

import com.spare.house.dao.EstateMongoDao;
import com.spare.house.dao.MongoDao;
import com.spare.house.model.Estate;
import com.spare.house.model.PageQuery;
import com.spare.house.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dada on 2017/7/16.
 */
@Component
public class EstateServiceImpl implements EstateService {

    @Autowired
    EstateMongoDao estateMongoDao;

    @Override
    public List<Estate> list(Estate where, PageQuery pageQuery) {
//        estateMongoDao.queryByName(where, pageQuery);
        return null;
    }

    @Override
    public List<Estate> listByName(String estateName) {
        return estateMongoDao.queryByName(estateName, new PageQuery());
    }

    @Override
    public List<Estate> listByDistrict(String district, PageQuery pageQuery) {
        return estateMongoDao.queryByDistrict(district, pageQuery);
    }
}
