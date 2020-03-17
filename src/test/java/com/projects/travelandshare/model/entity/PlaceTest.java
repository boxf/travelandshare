package com.projects.travelandshare.model.entity;

import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {

    @Test
    void testSetName_testGetName() {
    Place place = new Place();
    String resultExpected = "Calanques";
    place.setName("Calanques");
    assertEquals(resultExpected, place.getName());
        System.out.println(resultExpected);
        System.out.println(place.getName());
    }
}