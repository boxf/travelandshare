package com.projects.travelandshare.restcontroller;

import com.projects.travelandshare.model.entity.Place;

import com.projects.travelandshare.service.PlaceService;
import com.projects.travelandshare.service.exception.ConflictException;
import com.projects.travelandshare.util.Counties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200/")
public class PlaceRestController {

    @Autowired
    private PlaceService placeService;
    List<Place> placeList;

    public PlaceRestController (PlaceService placeService){
        this.placeService=placeService;
    }


    @RequestMapping("/place/{counties}")
    List<Place> getPlaceByCounty(@PathVariable("counties") Counties counties) {
        placeList = placeService.findPlaceByCounty(counties);
        return placeList;
    }

    @RequestMapping("/places")
    public List<Place> getAllPlace() {
        placeList = placeService.findAllPlace();
        return placeList;
    }

    @PostMapping(value = "/places", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addNewPlace(@RequestBody Place newPlace) throws ConflictException {
        try {
            placeService.registerPlace(newPlace);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
