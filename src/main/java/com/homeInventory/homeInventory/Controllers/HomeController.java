package com.homeInventory.homeInventory.Controllers;

import com.homeInventory.homeInventory.BusinessObjects.Home;
import com.homeInventory.homeInventory.mongo.HomeDoa;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    private MongoOperations mongoOperations;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createHome(@RequestBody Home home) {
        HomeDoa homeDoa = new HomeDoa();
        HttpStatus status = HttpStatus.OK;
        Home newHome = null;
        if(!home.validHome()) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            newHome = new Home();
            newHome.set_id(ObjectId.get());
            newHome.setName(home.getName());
            newHome.setOccupants(home.getOccupants());
            homeDoa.save(mongoOperations, newHome);
        }
        return new ResponseEntity<Home>(newHome, status);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<?> searchForHomes(@RequestParam String name, @RequestParam String occupants) {
        HomeDoa homeDoa = new HomeDoa();
        HttpStatus status = HttpStatus.OK;

        List<Home> foundHomes = homeDoa.findAll(mongoOperations, name, occupants);

        if (foundHomes.isEmpty()) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<List>(foundHomes, status);
    }
}