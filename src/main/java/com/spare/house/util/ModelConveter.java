package com.spare.house.util;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.spare.house.model.Estate;
import com.spare.house.model.House;
import com.spare.house.model.HouseTrend;
import org.apache.el.lang.ELArithmetic;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dada on 2017/7/25.
 */
public class ModelConveter {

    private static Logger logger = LoggerFactory.getLogger(ModelConveter.class);

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
            houseTrends.add(houseTrend);
        });
        return houseTrends;
    }

    public static List<Estate> convertToEstate(FindIterable<Document> iterable) {
        List<Estate> estateList = new ArrayList<>();
        if(iterable == null) {
            return estateList;
        }
        iterable.forEach((Block<Document>) document -> {
            Estate estate = new Estate();
            estate.setName(document.getString("name"));
            estate.setDistrict(document.getString("district"));
            estate.setAddress(document.getString("address"));
            estate.setLianjiaId(document.getString("lianjiaId"));
            estate.setLink(document.getString("houseLink"));
            estateList.add(estate);
        });
        return estateList;
    }

    public static List<House> convertToHouse(FindIterable<Document> iterable) {
        List<House> houseList = new ArrayList<>();
        if(iterable == null) {
            return houseList;
        }
        iterable.forEach((Block<Document>) document -> {
            House house = new House();
            house.setTitle(document.getString("title"));
            house.setLink(document.getString("link"));
            house.setEstateLianjiaId(document.getString("estateLianjiaId"));
            house.setEstateName(document.getString("estateName"));
            //FIXME time convert problem
//            house.setGmtCreated(document.getDate("gmtCreated"));
            try {
                Object priceObj = document.get("price");
                if(priceObj instanceof String) {
                    house.setPrice(Double.valueOf(document.getString("price")));
                } else if(priceObj instanceof Double) {
                    house.setPrice((Double) priceObj);
                }
            } catch (NumberFormatException e) {
                logger.error("Price of house {} {} is error {}", document.getString("_id"), house.getTitle(), document.getString("price"));
            }
            house.setCity(document.getString("city"));
            house.setHouseType(document.getString("houseType"));
            house.setArea(document.getString("area"));
            house.setFloor(document.getString("floor"));
            houseList.add(house);
        });
        return houseList;
    }

}
