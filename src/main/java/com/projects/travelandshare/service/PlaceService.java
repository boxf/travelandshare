package com.projects.travelandshare.service;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.repository.PlaceRepository;
import com.projects.travelandshare.service.exception.ConflictException;
import com.projects.travelandshare.util.Counties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

/**
 * A service class which defines the required method on Place
 */

@Service
public class PlaceService {

    /**
     * Dependencies injection of PlaceRepository
     */
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private StorageService storageService;

    List<Place> placeList;


    public Place registerPlace(Place place) {
        Place placeFound = placeRepository.findPlaceByName(place.getName());
        if (placeFound == null){
            return this.placeRepository.save(place);
        }
        else {
            throw new ConflictException();
        }
    }

    /**
     * Used to find a list of places thanks to theirs counties in the dataBase
     * @param counties: enumeration of counties
     * @return a list of places
     * @author Dambrine François
     */
    public List<Place> findPlaceByCounty(Counties counties){
        return placeRepository.findAllByCounty(counties);
    }
    /**
     * Used to find all the places which are in the dataBase
     * @return a list of all the places
     * @author Dambrine François
     */
    public List<Place> findAllPlace (){
        return (List<Place>) placeRepository.findAll();
    }

    public Optional<Place> findPlaceById(Long id) {
        return placeRepository.findById(id);
    }
}
