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

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200/")
public class PlaceRestController {

    @Autowired
    private PlaceService placeService;


    @RequestMapping("/place/{counties}")
    List<Place> getPlaceByCounty(@PathVariable("counties") Counties counties){
        List<Place> placeList = placeService.findPlaceByCounty(counties);
       return placeList;
    }

    @RequestMapping ("/places")
    public List<Place> getAllPlace() {
        List<Place> placeList = placeService.findAllPlace();
        return placeList;
    }

}
