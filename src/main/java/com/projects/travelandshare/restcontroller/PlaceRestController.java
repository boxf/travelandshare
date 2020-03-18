package com.projects.travelandshare.restcontroller;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.repository.PlaceRepository;
import com.projects.travelandshare.service.PlaceService;
import com.projects.travelandshare.util.Counties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RestController for Place. It permish to get back the information in the model and communicate with the view
 */
@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200/")
public class PlaceRestController {

    /**
     * Dependencies injection of PlaceService
     */
    @Autowired
    private PlaceService placeService;


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

}
