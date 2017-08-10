package com.spare.house.service.impl;

import com.spare.house.dao.HouseTrendMongoDao;
import com.spare.house.dao.MongoDao;
import com.spare.house.model.House;
import com.spare.house.model.HouseTrend;
import com.spare.house.model.PageQuery;
import com.spare.house.service.HouseTrendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dada on 2017/7/25.
 */
@Component
public class HouseTrendServiceImpl implements HouseTrendService {

    private static Logger logger = LoggerFactory.getLogger(HouseTrendServiceImpl.class);

    private static final long ONE_WEEK_MILLIS = 7 * 86400 * 1000;

    @Autowired
    HouseTrendMongoDao houseTrendMongoDao;

    @Override
    public List<HouseTrend> list(HouseTrend where, PageQuery pageQuery) {
        List<HouseTrend> houseTrendList = houseTrendMongoDao.queryByHouseId(where.getHouseId(), pageQuery);
        formatTrends(houseTrendList);
        return houseTrendList;
    }

    private void formatTrends(List<HouseTrend> houseTrendList) {
        if(CollectionUtils.isEmpty(houseTrendList)) {
            return;
        }

        for (HouseTrend houseTrend : houseTrendList) {
            if(CollectionUtils.isEmpty(houseTrend.getTrendList())) {
                HouseTrend.Trend empty = houseTrend.new Trend();
                empty.setPrice("0");
                empty.setDate(new Date());
                empty.setDateStr(formatDate(empty.getDate()));
                houseTrend.setTrendList(new ArrayList<>());
                houseTrend.getTrendList().add(empty);
            } else {
                if(houseTrend.getTrendList().size() == 1) {
                    // only one price, need padding same one
                    HouseTrend.Trend padding = houseTrend.new Trend();
                    Date curDate = houseTrend.getTrendList().get(0).getDate();
                    houseTrend.getTrendList().get(0).setDateStr(formatDate(curDate));
                    padding.setDate(new Date(curDate.getTime() - ONE_WEEK_MILLIS));
                    padding.setPrice(houseTrend.getTrendList().get(0).getPrice());
                    padding.setDateStr(formatDate(padding.getDate()));
                    List<HouseTrend.Trend> newList = new ArrayList<>();
                    newList.add(padding);
                    newList.add(houseTrend.getTrendList().get(0));
                    houseTrend.setTrendList(newList);
                } else {
                    // only need format date
                    for(HouseTrend.Trend trend : houseTrend.getTrendList()) {
                        trend.setDateStr(formatDate(trend.getDate()));
                    }
                }
            }
        }
    }

    private String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        return simpleDateFormat.format(date);
    }


}
