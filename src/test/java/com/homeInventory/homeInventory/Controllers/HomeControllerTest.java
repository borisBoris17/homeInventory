package com.homeInventory.homeInventory.Controllers;

import com.homeInventory.homeInventory.BusinessObjects.Home;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTest {

    HomeController subject;

    @Before
    public void setUp() {
        subject = new HomeController();
    }

    @Test
    public void testCreateHome_nameAndOccupantsSetOnHome_OkStatus() {
        Home home = new Home();
        home.setName("name");
        home.setOccupants("occupants");

        ResponseEntity<Home> actual = (ResponseEntity<Home>) subject.createHome(home);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("name", actual.getBody().getName());
        assertEquals("occupants", actual.getBody().getOccupants());
    }

    @Test
    public void testCreateHome_nullName_BadRequestStatus() {
        Home home = new Home();
        home.setName(null);
        home.setOccupants("occupants");

        ResponseEntity<Home> actual = (ResponseEntity<Home>) subject.createHome(home);
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        assertEquals(null, actual.getBody());
    }

    @Test
    public void testCreateHome_nullOccupants_BadRequestStatus() {
        Home home = new Home();
        home.setName("home");
        home.setOccupants(null);

        ResponseEntity<Home> actual = (ResponseEntity<Home>) subject.createHome(home);
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        assertEquals(null, actual.getBody());
    }

    @Test
    public void testCreateHome_nullNameAndOccupants_BadRequestStatus() {
        Home home = new Home();
        home.setName(null);
        home.setOccupants(null);

        ResponseEntity<Home> actual = (ResponseEntity<Home>) subject.createHome(home);
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        assertEquals(null, actual.getBody());
    }
}