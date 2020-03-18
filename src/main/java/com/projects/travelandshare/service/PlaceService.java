package com.projects.travelandshare.service;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.repository.PlaceRepository;
import com.projects.travelandshare.util.Counties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service classe which defines the required method on Place
 */
@Service
public class PlaceService {

    /**
     * Dependencies injection of PlaceRepository
     */
    @Autowired
    private PlaceRepository repository;


    /**
     * Used to find a list of places thanks to theirs counties in the dataBase
     * @param counties: enumeration of counties
     * @return a list of places
     * @author Dambrine François
     */
    public List<Place> findPlaceByCounty(Counties counties){
        List<Place> placeList = repository.findAllByCounty(counties);
        return placeList;
    }

    /**
     * Used to find all the places which are in the dataBase
     * @return a list of all the places
     * @author Dambrine François
     */
    public List<Place> findAllPlace (){
        List<Place> placeList = (List<Place>) repository.findAll();
        return placeList;
    }
}
