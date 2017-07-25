package com.spare.house.util;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.spare.house.model.HouseTrend;
import org.bson.Document;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dada on 2017/7/25.
 */
public class ModelConveter {

    public static List<HouseTrend> convertToHouseTrend(FindIterable<Document> iterable) {
        List<HouseTrend> houseTrends = new ArrayList<>();
        if(iterable == null) {
            return houseTrends;
        }
        iterable.forEach((Block<Document>) document -> {
            HouseTrend houseTrend = new HouseTrend();
            houseTrend.setLink(document.getString("houseLink"));
            houseTrend.setTitle(document.getString("title"));
            houseTrend.setTrendList(new ArrayList<>());
            if(document.get("trend") != null) {
                List<Document> trends = (List<Document>) document.get("trend");
                if(! CollectionUtils.isEmpty(trends)) {
                    for (Document docTrend : trends) {
                        HouseTrend.Trend trend = houseTrend.new Trend();
                        trend.setDate(docTrend.getString("date"));
                        trend.setPrice(docTrend.getString("price"));
                        houseTrend.getTrendList().add(trend);
                    }
                }
            }
//            document.get
            houseTrends.add(houseTrend);
        });
        return houseTrends;
    }

}
