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

    public List<Home> findAllByName(MongoOperations mongoOperations, String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        return mongoOperations.find(query, Home.class);
    }

    public List<Home> findAllByOccupants(MongoOperations mongoOperations, String occupants) {
        Query query = new Query();
        query.addCriteria(Criteria.where("occupants").is(occupants));

        return mongoOperations.find(query, Home.class);
    }
}
