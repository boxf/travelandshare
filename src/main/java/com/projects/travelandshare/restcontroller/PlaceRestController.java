package com.projects.travelandshare.restcontroller;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.repository.PlaceRepository;
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
    @Autowired
    private StorageService storageService;
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
    List<Place> getPlaceByCounty(@PathVariable("counties") Counties counties) {
        placeList = placeService.findPlaceByCounty(counties);
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
        placeList = placeService.findAllPlace();
        return placeList;
    }

    @PostMapping(value = "/addPlaces", consumes ="multipart/form-data")
    public ResponseEntity<Object> addNewPlace(@ModelAttribute Place newPlace, @RequestParam(value ="file",
            required = false)MultipartFile file, Model model) throws ConflictException {
        try {
            if(file!= null && !file.isEmpty()){
                placeService.registerPlace(newPlace, file);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } else {
                placeService.registerPlace(newPlace);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }

        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
