package com.spare.house.dao;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.spare.house.model.House;
import com.spare.house.model.PageQuery;
import com.spare.house.util.ModelConveter;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dada on 2017/7/29.
 */
@Component
public class HouseMongoDao {

    private static Logger logger = LoggerFactory.getLogger(HouseMongoDao.class);

    @Autowired
    MongoDao mongoDao;

    public List<House> list(House house, PageQuery pageQuery) {
        if(pageQuery == null) {
            pageQuery = new PageQuery();
        }

        MongoCollection<Document> collection = mongoDao.getMongoDatabase().getCollection("house.current");
        try {
            List<Bson> filters = new ArrayList<>();
            if(house.getEstateName() != null) {
                filters.add(Filters.eq(MongoConstants.MONGO_FILED_HOUSE_ESTATE_NAME, house.getEstateName()));
            }
            if(house.getHouseId() != null) {
                filters.add(Filters.regex("link", ".*" + house.getHouseId() + ".*"));
            }

            FindIterable<Document> iterable = collection
                    .find(Filters.and(filters))
                            .skip(pageQuery.getSkip()).limit(pageQuery.getPerPageCount()).sort(Filters.eq("_id", -1));
            if(iterable == null) {
                logger.info("Can not find house for {}", house);
                return null;
            }
            return ModelConveter.convertToHouse(iterable);
        } catch (Exception e) {
            logger.error("ERROR on query estate ", e);
        }
        return new ArrayList<>();
    }

}
