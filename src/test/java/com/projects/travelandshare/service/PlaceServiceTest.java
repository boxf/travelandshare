package com.projects.travelandshare.service;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.repository.PlaceRepository;
import com.projects.travelandshare.service.exception.ConflictException;
import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.fail;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.asserts.Assertion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {
   @InjectMocks
   PlaceService placeService;

   @Mock
   PlaceRepository placeRepository;

   @Before
   public void init(){
//       MockitoAnnotations.initMocks(this);
   }

    @Test
    void givenNewPlace_whenPlaceDoesntExist_RegisterPlace() {
        Place place = new Place("Nice",3.2,5.6,"Nice.png");

        placeService.registerPlace(place);

        verify(placeRepository, times(1)).save(place);
    }
    @Test
    void givenNewPlace_whenPlaceExist_ThrowsException() {
    Place place = new Place("Paris", 3.52, 6.23, "paris.png");
    Place place2 = new Place("Paris", 3.6, 5.23, "paris2.png");

    placeService.registerPlace(place);
    placeService.registerPlace(place2);

    Assertions.assertThrows(ConflictException.class, () -> {
        place.getName().equals(place2.getName());

    });
    }
    // Le test passe mais ne fonctionne pas en réalité.
    @Test
    void testFindPlaceByCounty() {
        List <Place> expectedList = new ArrayList<>();
        List <Place> countiesList = new ArrayList<>();
        Place place = new Place("Calanques", Counties.AIN_01, Types.LOWMOUNTAIN,3.50, 4.23, "Calanques.png");
        Place place1 = new Place("Paris", Counties.AIN_01, Types.MUSEUM, 0.65, 5.36, "Paris.png");
        Place place2 = new Place("Lyon", Counties.AUBE_10, Types.BEACH, 2.35, 5.63, "Lyon.png");
        expectedList.add(place);
        expectedList.add(place1);
        countiesList.add(place);
        countiesList.add(place1);
        countiesList.add(place2);

        countiesList =  placeRepository.findAllByCounty(Counties.AIN_01);
        when(placeRepository.findAllByCounty(Counties.AIN_01)).thenReturn(countiesList);

        assertEquals(expectedList.size(), StreamSupport.stream(countiesList.spliterator(), false).count());
        if (expectedList.size() != (StreamSupport.stream(countiesList.spliterator(), false).count())){
            fail("Some place are not suppose to be there");
        }
    }

    @Test
    void testFindAllPlace() {
        List <Place> expectedList = new ArrayList<Place>();
        Place place = new Place("Calanques", 0.35, 6.52, "Calanques.png");
        Place place1 = new Place("Paris", 0.65, 5.36, "Paris.png");
        Place place2 = new Place("Lyon",2.35, 5.63, "Lyon.png");
        expectedList.add(place);
        expectedList.add(place1);
        expectedList.add(place2);

        when(placeRepository.findAll()).thenReturn(expectedList);

       Iterable<Place> testList = placeRepository.findAll();

        assertEquals(expectedList.size(), StreamSupport.stream(testList.spliterator(), false).count());
        if (expectedList.size() != (StreamSupport.stream(testList.spliterator(), false).count())){
            fail("All the Place aren't return by the method");
        }
    }

}