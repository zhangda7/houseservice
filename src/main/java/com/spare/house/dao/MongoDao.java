package com.spare.house.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.spare.house.model.Estate;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dada on 2017/7/16.
 */
public class MongoDao {

    private static Logger logger = LoggerFactory.getLogger(MongoDao.class);

    private MongoClient mongoClient;

    private MongoDatabase mongoDatabase;

    private String uri;

    private String database;

    public MongoDao(String host, int port, String database) {
        this.uri = "mongodb://" + host + ":" + port;
        this.database = database;
    }

    public void init() {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(20).maxWaitTime(20000);

        mongoClient = new MongoClient(new MongoClientURI(uri, builder));
        mongoDatabase = mongoClient.getDatabase(database);

    }

    public void queryEstate(Estate estate) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("estate.detail");
        try {
            Document document = collection.find(Filters.eq(MongoConstants.MONGO_FIELD_ESTATE_NAME, estate.getName())).first();
            if(document == null) {
                logger.info("Can not find estate for {}", estate.getName());
                return;
            }
            logger.info("Find estate {} for {}", document, estate.getName());
        } catch (Exception e) {
            logger.error("ERROR on query estate ", e);
        }
    }
}
