package com.projects.travelandshare.model.entity;

import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {

    @Test
    void testSetName_setsProperly() throws NoSuchFieldException, IllegalAccessException {
//given
        final Place place = new Place();
//when
        place.setName("Calanques");
//then
        final Field field = place.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals("Calanques", field.get(place));
        System.out.println(field.get(place));
    }
    @Test
    public void testGetName_getsValues() throws NoSuchFieldException, IllegalAccessException {
//given
        final Place place = new Place();
        final Field field = place.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(place, "Paris");
//when
        final String result = place.getName();
//then
        assertEquals("Paris", result);
        System.out.println(place.getName());
    }
}
