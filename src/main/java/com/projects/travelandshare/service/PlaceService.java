package com.projects.travelandshare.service;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.repository.PlaceRepository;
import com.projects.travelandshare.util.Counties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository repository;
    private Place place;

    public List<Place> findPlaceByCounty(Counties counties){
        List<Place> placeList = repository.findAllByCounty(counties);
        return placeList;
    }
    public List<Place> findAllPlace (){
        List<Place> placeList = (List<Place>) repository.findAll();
        return placeList;
    }

}
