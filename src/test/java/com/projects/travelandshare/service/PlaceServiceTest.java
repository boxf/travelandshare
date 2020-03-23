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
    }

    @Test
    void givenNewPlace_whenPlaceDoesntExist_RegisterPlace() {
        //Given
        Place place = new Place("Nice",Counties.ALPESMARITIMES_06, Types.BEACH, 3.2,5.6);
        when(placeRepository.save(place)).thenReturn(place);

        //When
        placeService.registerPlace(place);

        //Then
        verify(placeRepository, times(1)).save(place);
    }

    @Test
    void givenNewPlace_whenPlaceExist_ThrowsException() {
        //Given
        Place mockedPlace = new Place("Paris", Counties.PARIS_75, Types.MUSEUM, 3.52, 6.23);
        when(placeRepository.findPlaceByName("Paris")).thenReturn(mockedPlace);

        //When-Then
        Assertions.assertThrows(ConflictException.class, () -> {
            Place place2 = new Place("Paris",Counties.PARIS_75,Types.MUSEUM,3.6, 5.23);
            placeService.registerPlace(place2);
        });
    }

    @Test
    void givenPlaces_whenFindByCounties_thenReturnMatchesPlaces() {
        //Given
        List <Place> countiesList = new ArrayList<>();
        Place place = new Place("Calanques", Counties.BOUCHESDURHÔNE_13, Types.MEDIUMMOUNTAIN,3.50, 4.23);
        Place place1 = new Place("Marseille", Counties.BOUCHESDURHÔNE_13, Types.MUSEUM, 0.65, 5.36);
        countiesList.add(place);
        countiesList.add(place1);
        when(placeRepository.findAllByCounty(Counties.BOUCHESDURHÔNE_13)).thenReturn(countiesList);

        //when
        List<Place> expectedList = placeService.findPlaceByCounty(Counties.BOUCHESDURHÔNE_13);

        //then
        assertEquals(expectedList.size(), StreamSupport.stream(countiesList.spliterator(), false).count());
        if (expectedList.size() != (StreamSupport.stream(countiesList.spliterator(), false).count())){
            fail("Some place are not suppose to be there");
        }
    }

    @Test
    void givenPlaces_whenFindAll_thenReturnAllPlace() {
        //Given
        List <Place> expectedList = new ArrayList<Place>();
        Place place = new Place("Calanques", Counties.BOUCHESDURHÔNE_13, Types.MEDIUMMOUNTAIN,3.50, 4.23);
        Place place1 = new Place("Paris", Counties.PARIS_75, Types.MUSEUM, 0.65, 5.36);
        Place place2 = new Place("Lyon", Counties.RHÔNE_69, Types.ARTGALLERY, 2.35, 5.63);
        expectedList.add(place);
        expectedList.add(place1);
        expectedList.add(place2);
        when(placeRepository.findAll()).thenReturn(expectedList);

        //When
        Iterable<Place> testList = placeRepository.findAll();

        //Then
        assertEquals(expectedList.size(), StreamSupport.stream(testList.spliterator(), false).count());
        if (expectedList.size() != (StreamSupport.stream(testList.spliterator(), false).count())){
            fail("All the Place aren't return by the method");
        }
    }

}