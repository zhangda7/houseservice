package com.spare.house.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.spare.house.model.HouseTrend;
import com.spare.house.model.PageQuery;
import com.spare.house.util.ModelConveter;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dada on 2017/8/8.
 */
@Component
public class HouseTrendMongoDao {

    private static Logger logger = LoggerFactory.getLogger(HouseTrendMongoDao.class);

    @Autowired
    MongoDao mongoDao;

    public List<HouseTrend> queryByHouseId(String houseId, PageQuery pageQuery) {
        MongoCollection<Document> collection = mongoDao.getMongoDatabase().getCollection("house.result");
        try {
            FindIterable<Document> iterable;
            iterable = collection
                    .find(Filters.regex(MongoConstants.MONGO_FIELD_HOUSE_LINK, ".*" + houseId + ".*")).
                            skip(pageQuery.getSkip()).limit(pageQuery.getPerPageCount());
            if(iterable == null) {
                logger.info("Can not find estate for {}", houseId);
                return null;
            }
//            iterable.forEach((Block<Document>) document -> {
//                logger.info("Find {}", document);
//            });
            return ModelConveter.convertToHouseTrend(iterable);

//            logger.info("Find estate {} for {}", document, where.getName());
        } catch (Exception e) {
            logger.error("ERROR on query estate ", e);
        }
        return null;
    }

}
