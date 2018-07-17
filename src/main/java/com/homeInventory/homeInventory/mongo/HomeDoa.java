package com.homeInventory.homeInventory.mongo;

import com.homeInventory.homeInventory.BusinessObjects.Home;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class HomeDoa {

    public void save(MongoOperations mongoOperations, Home home) {
        mongoOperations.save(home);
    }

    public List<Home> findAll(MongoOperations mongoOperations, String name, String occupants) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name).and("occupants").is(occupants));

        return mongoOperations.find(query, Home.class);
    }
}
