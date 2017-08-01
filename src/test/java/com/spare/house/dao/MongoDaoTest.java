package com.spare.house.dao;

import com.spare.house.model.Estate;
import com.spare.house.model.House;
import com.spare.house.model.PageQuery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by dada on 2017/7/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
public class MongoDaoTest {

    @Autowired
    EstateMongoDao estateMongoDao;

    @Autowired
    HouseMongoDao houseMongoDao;

    @Before
    public void before() {
    }

    @Test
    public void testQueryEstate() {
        Estate estate = new Estate();
        estate.setName("万科");
        List<Estate> estateList = estateMongoDao.queryByName("绿地", new PageQuery());
        Assert.assertTrue(estateList.size() > 0);
    }

    @Test
    public void testQueryHouse() {
        House house = new House();
        house.setEstateName("印象春城");
        List<House> houseList = houseMongoDao.list(house, null);
        Assert.assertTrue(houseList.size() > 0);
    }

}
