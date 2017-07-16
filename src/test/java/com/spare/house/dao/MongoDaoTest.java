package com.spare.house.dao;

import com.spare.house.model.Estate;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by dada on 2017/7/16.
 */
public class MongoDaoTest {

    MongoDao mongoDao;

    @Before
    public void before() {
        mongoDao = new MongoDao("127.0.0.1", 27017, "lianjia");
        mongoDao.init();
    }

    @Test
    public void testQueryEstate() {
        Estate estate = new Estate();
        estate.setName("万科提香别墅");
        mongoDao.queryEstate(estate);
    }

}
