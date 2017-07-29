package com.spare.house.dao;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.spare.house.model.Estate;
import com.spare.house.model.PageQuery;
import com.spare.house.util.ModelConveter;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

}
