package com.spare.house.dao;

import com.alibaba.fastjson.JSON;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.spare.house.model.Estate;
import com.spare.house.model.PageQuery;
import com.spare.house.util.ModelConveter;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dada on 2017/7/27.
 */
@Component
public class EstateMongoDao {

    private static Logger logger = LoggerFactory.getLogger(EstateMongoDao.class);

    @Autowired
    MongoDao mongoDao;

    public List<Estate> list(Estate estate, PageQuery pageQuery) {
        return null;
    }

    public List<Estate> queryByName(String estateName, PageQuery pageQuery) {
        MongoCollection<Document> collection = mongoDao.getMongoDatabase().getCollection("estate.detail");
        try {
            FindIterable<Document> iterable = collection
                    .find(Filters.regex(MongoConstants.MONGO_FIELD_ESTATE_NAME, ".*" + estateName + ".*")).
                            skip(pageQuery.getSkip()).limit(pageQuery.getPerPageCount());
            if(iterable == null) {
                logger.info("Can not find estate for {}", estateName);
                return null;
            }
            return ModelConveter.convertToEstate(iterable);
        } catch (Exception e) {
            logger.error("ERROR on query estate ", e);
        }
        return new ArrayList<>();
    }

    public List<Estate> queryByDistrict(String district, PageQuery pageQuery) {
        MongoCollection<Document> collection = mongoDao.getMongoDatabase().getCollection("estate.detail");
        try {
            FindIterable<Document> iterable = collection
                    .find(Filters.regex(MongoConstants.MONGO_FIELD_DISTRICT, ".*" + district + ".*")).
                            skip(pageQuery.getSkip()).limit(pageQuery.getPerPageCount());
            if(iterable == null) {
                logger.info("Can not find estate for {}", district);
                return null;
            }
            return ModelConveter.convertToEstate(iterable);
        } catch (Exception e) {
            logger.error("ERROR on query estate ", e);
        }
        return new ArrayList<>();
    }

    public void fillLatestPrice(List<Estate> estateList) {
        if(estateList == null) {
            return;
        }
        List<Bson> filters = new ArrayList<>();
        Map<String, Estate> estateMap = new HashMap<>();
        for (Estate estate : estateList) {
            filters.add(Filters.eq(MongoConstants.MONGO_FILED_HOUSE_ESTATE_NAME, estate.getName()));
            estateMap.put(estate.getName(), estate);
            estate.setPrice(0.0);
        }
        MongoCollection<Document> collection = mongoDao.getMongoDatabase().getCollection("estate.trend");

        FindIterable<Document> iterable = collection.find(Filters.or(filters));
        if(iterable == null) {
            logger.error("Can not fill price for {}", JSON.toJSONString(estateList));
            return;
        }

        iterable.forEach((Block<Document>) document -> {
            String estateName = document.getString("estateName");
            List<Document> documents = (List<Document>) document.get("trends");
            if(documents == null) {
                return;
            }
            double price = documents.get(documents.size() - 1).getDouble("price");
            estateMap.get(estateName).setPrice(price);
        });


    }

}
