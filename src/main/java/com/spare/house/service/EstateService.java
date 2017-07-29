package com.spare.house.service;

import com.spare.house.model.Estate;
import com.spare.house.model.PageQuery;

import java.util.List;

/**
 * Created by dada on 2017/7/16.
 */
public interface EstateService {

    List<Estate> list(Estate where, PageQuery pageQuery);

    /**
     * List by name, default max count 50
     * @param estateName
     * @return
     */
    List<Estate> listByName(String estateName);

    /**
     * List by district, default max count 50
     * @param district
     * @return
     */
    List<Estate> listByDistrict(String district, PageQuery pageQuery);

}
