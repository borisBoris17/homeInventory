package com.homeInventory.homeInventory.Repositories;

import com.homeInventory.homeInventory.BusinessObjects.Home;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "home", path = "home")
public interface HomeRepository extends MongoRepository<Home, String>{
    List<Home> findByName(@Param("name") String name);
}
