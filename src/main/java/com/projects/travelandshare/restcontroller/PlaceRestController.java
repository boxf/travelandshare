package com.projects.travelandshare.restcontroller;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.repository.PlaceRepository;
import com.projects.travelandshare.service.PlaceService;
import com.projects.travelandshare.service.exception.ConflictException;
import com.projects.travelandshare.util.Counties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for Place. It permish to get back the information in the model and communicate with the view
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class PlaceRestController {

    /**
     * Dependencies injection of PlaceService
     */
    @Autowired
    private PlaceService placeService;
    List<Place> placeList;

    public PlaceRestController (PlaceService placeService){
        this.placeService=placeService;
    }


    /**
     * Method for have a list of places by their counties. It works with the url ".../api/place/" + the county
     * @param counties: enumeration of counties
     * @return a list of places on a HTTP page
     * @author Dambrine François
     */
    @RequestMapping("/place/{counties}")
    List<Place> getPlaceByCounty(@PathVariable("counties") Counties counties){
        List<Place> placeList = placeService.findPlaceByCounty(counties);
        return placeList;
    }

    /**
     *
     * Methode for have the all places in the dataBase. It works with the url ".../api/places"
     * @return a list of all the places on a HTTP page
     * @author Dambrine François
     */
    @RequestMapping ("/places")
    public List<Place> getAllPlace() {
        List<Place> placeList = placeService.findAllPlace();
        return placeList;
    }

    @PostMapping(value = "/place", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addNewPlace(@RequestBody Place newPlace) throws ConflictException {
        try {
            placeService.registerPlace(newPlace);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
