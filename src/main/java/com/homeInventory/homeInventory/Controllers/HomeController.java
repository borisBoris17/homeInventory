package com.homeInventory.homeInventory.Controllers;

import com.homeInventory.homeInventory.BusinessObjects.Home;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createHome(@RequestBody Home home) {
        HttpStatus status = HttpStatus.OK;
        Home newHome = new Home();
        if(!home.validHome()) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            newHome.setName(home.getName());
            newHome.setOccupants(home.getOccupants());
        }
        return new ResponseEntity<Home>(newHome, status);
    }
}