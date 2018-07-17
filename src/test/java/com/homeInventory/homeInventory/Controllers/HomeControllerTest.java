package com.homeInventory.homeInventory.Controllers;

import com.homeInventory.homeInventory.BusinessObjects.Home;
import com.homeInventory.homeInventory.mongo.HomeDoa;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTest {

    HomeController subject;
    @Mocked
    HomeDoa homeDoa;
    @Mocked
    MongoOperations mongoOperations;

    @Before
    public void setUp() {
        subject = new HomeController();
    }

    @Test
    public void testCreateHome_nameAndOccupantsSetOnHome_OkStatus() {
        Home home = new Home();
        home.setName("name");
        home.setOccupants("occupants");

        new Expectations() {{
            homeDoa.save((MongoOperations) any, (Home) any);
            times = 1;
        }};

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

        new Expectations() {{
            homeDoa.save((MongoOperations) any, (Home) any);
            times = 0;
        }};

        ResponseEntity<Home> actual = (ResponseEntity<Home>) subject.createHome(home);
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        assertEquals(null, actual.getBody());
    }

    @Test
    public void testCreateHome_nullOccupants_BadRequestStatus() {
        Home home = new Home();
        home.setName("home");
        home.setOccupants(null);

        new Expectations() {{
            homeDoa.save((MongoOperations) any, (Home) any);
            times = 0;
        }};

        ResponseEntity<Home> actual = (ResponseEntity<Home>) subject.createHome(home);
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        assertEquals(null, actual.getBody());
    }

    @Test
    public void testCreateHome_nullNameAndOccupants_BadRequestStatus() {
        Home home = new Home();
        home.setName(null);
        home.setOccupants(null);

        new Expectations() {{
            homeDoa.save((MongoOperations) any, (Home) any);
            times = 0;
        }};

        ResponseEntity<Home> actual = (ResponseEntity<Home>) subject.createHome(home);
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        assertEquals(null, actual.getBody());
    }

    @Test
    public void testSearchForHomes_noResultsFound_badRequestStatus() {
        Home home = new Home();
        home.setName("name");
        home.setOccupants("occupants");
        Collection<Home> foundHomes = new ArrayList<>();

        new Expectations(){{
            homeDoa.findAll((MongoOperations) any, anyString, anyString);
            result = foundHomes;
        }};

        ResponseEntity<Collection> actual = (ResponseEntity<Collection>) subject.searchForHomes("name", "occupants");
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        assertEquals(0, actual.getBody().size());
    }

    @Test
    public void testSearchForHomes_resultsFound_okRequestStatus() {
        Home home = new Home();
        home.setName("name");
        home.setOccupants("occupants");
        ArrayList<Home> foundHomes = new ArrayList<>();
        foundHomes.add(new Home());

        new Expectations(){{
            homeDoa.findAll((MongoOperations) any, anyString, anyString);
            result = foundHomes;
        }};

        ResponseEntity<ArrayList> actual = (ResponseEntity<ArrayList>) subject.searchForHomes("name", "occupants");
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(1, actual.getBody().size());
    }
}