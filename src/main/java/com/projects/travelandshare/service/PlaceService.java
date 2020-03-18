package com.projects.travelandshare.service;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.repository.PlaceRepository;
import com.projects.travelandshare.service.exception.ConflictException;
import com.projects.travelandshare.util.Counties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;
    List<Place> placeList;


    public void registerPlace(Place place) {
        if(placeRepository.findPlaceByName(place.getName()) == null)
        this.placeRepository.save(place);
        else{
            throw new ConflictException();

        }
    }
    public List<Place> findAllPlace (){
        placeList = (List<Place>) placeRepository.findAll();
        return placeList;
    }


    public List<Place> findPlaceByCounty(Counties counties){
        placeList = placeRepository.findAllByCounty(counties);
        return placeList;
    }
}
