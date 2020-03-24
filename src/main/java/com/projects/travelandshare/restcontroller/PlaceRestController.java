package com.projects.travelandshare.restcontroller;

import com.projects.travelandshare.model.dto.CreatePlaceDTO;
import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.service.PlaceDTOService;
import com.projects.travelandshare.service.PlaceService;
import com.projects.travelandshare.service.StorageService;
import com.projects.travelandshare.service.exception.ConflictException;
import com.projects.travelandshare.util.Counties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * RestController for Place. It permish to get back the information in the model and communicate with the view
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")

public class PlaceRestController {

    /**
     * Dependencies injection of PlaceService
     */
    @Autowired
    private PlaceService placeService;
    @Autowired
    private PlaceDTOService placeDTOService;

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
    @RequestMapping("/places/{counties}")
    List<Place> getPlaceByCounty(@PathVariable("counties") Counties counties){
        List<Place> placeList = placeService.findPlaceByCounty(counties);
       return placeList;
    }

    /**
     *
     * Method that return one place from the DB. It works with the url ".../api/place/id"
     * @return the place with the corresponding id
     * @author Boxebeld Frédéric
     */
    @RequestMapping("/place/{id}")
    Optional<Place> getPlaceById(@PathVariable("id") Long id){
        Optional<Place> place = placeService.findPlaceById(id);
        return place;
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
    @PostMapping(value = "/place")
    public ResponseEntity<Object> addNewPlace(@ModelAttribute CreatePlaceDTO newPlace) throws ConflictException {
        try {
                Place newPlaceEntity = placeDTOService.placeDTOCopyPlaceEntity(newPlace);
                newPlaceEntity = placeService.registerPlace(newPlaceEntity);
                return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
