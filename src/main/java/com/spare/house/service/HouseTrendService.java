package com.spare.house.service;

import com.spare.house.model.HouseTrend;
import com.spare.house.model.PageQuery;

import java.util.List;

/**
 * Created by dada on 2017/7/25.
 */
public interface HouseTrendService {

    List<HouseTrend> list(HouseTrend where, PageQuery pageQuery);

}
