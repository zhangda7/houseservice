package com.spare.house.service;

import com.spare.house.model.Estate;
import com.spare.house.model.PageQuery;

import java.util.List;

/**
 * Created by dada on 2017/7/16.
 */
public interface EstateService {

    List<Estate> list(Estate where, PageQuery pageQuery);

}
