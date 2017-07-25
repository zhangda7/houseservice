package com.spare.house.service.impl;

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
    MongoDao mongoDao;

    @Override
    public List<Estate> list(Estate where, PageQuery pageQuery) {
        mongoDao.queryEstate(where, pageQuery);
        return null;
    }
}
