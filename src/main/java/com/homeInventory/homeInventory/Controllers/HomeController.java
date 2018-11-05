package com.homeInventory.homeInventory.Controllers;

import com.homeInventory.homeInventory.BusinessObjects.Home;
import com.homeInventory.homeInventory.mongo.HomeDao;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    private MongoOperations mongoOperations;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity<?> findHomeById(@RequestParam String id) {
        HomeDao homeDao = new HomeDao();
        Home foundHome = null;
        HttpStatus status = HttpStatus.OK;
        if (StringUtils.isEmpty(id)) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            foundHome = homeDao.findById(mongoOperations, id);
        }
        return new ResponseEntity<Home>(foundHome, status);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createHome(@RequestBody Home home) {
        HomeDao homeDao = new HomeDao();
        HttpStatus status = HttpStatus.OK;
        Home newHome = null;
        if(!home.validHome()) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            newHome = new Home();
            newHome.set_id(ObjectId.get());
            newHome.setName(home.getName());
            newHome.setOccupants(home.getOccupants());
            homeDao.save(mongoOperations, newHome);
        }
        return new ResponseEntity<Home>(newHome, status);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/searchByName", method = RequestMethod.GET)
    public ResponseEntity<?> searchForHomesByName(@RequestParam String name) {
        HomeDao homeDao = new HomeDao();
        HttpStatus status = HttpStatus.OK;

        List<Home> foundHomes = homeDao.findAllByName(mongoOperations, name);

        if (foundHomes.isEmpty()) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<List>(foundHomes, status);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/searchByOccupants", method = RequestMethod.GET)
    public ResponseEntity<?> searchForHomesByOccupants(@RequestParam String occupants) {
        HomeDao homeDao = new HomeDao();
        HttpStatus status = HttpStatus.OK;

        List<Home> foundHomes = homeDao.findAllByOccupants(mongoOperations, occupants);

        if (foundHomes.isEmpty()) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<List>(foundHomes, status);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<?> searchForHomes(@RequestParam String name, @RequestParam String occupants) {
        HomeDao homeDao = new HomeDao();
        HttpStatus status = HttpStatus.OK;

        List<Home> foundHomes = homeDao.findAll(mongoOperations, name, occupants);

        if (foundHomes.isEmpty()) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<List>(foundHomes, status);
    }
}