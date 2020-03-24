package com.projects.travelandshare.service;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.repository.PlaceRepository;
import com.projects.travelandshare.service.exception.ConflictException;
import com.projects.travelandshare.util.Counties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private StorageService storageService;
    List<Place> placeList;


    public void registerPlace(Place place) {
       Place placeFound = placeRepository.findPlaceByName(place.getName());
        if (placeFound == null){
            this.placeRepository.save(place);
        }
        else {
            throw new ConflictException();

        }
    }

    public void registerPlace(Place place, MultipartFile file) {
        if (placeRepository.findPlaceByName(place.getName()) == null) {
            this.storageService.savePicture(file);
            place.setPictureName(file.getOriginalFilename());
            this.registerPlace(place);

        } else {
            throw new ConflictException();

        }
    }

    public List<Place> findAllPlace() {
        placeList = (List<Place>) placeRepository.findAll();
        return placeList;
    }


    public List<Place> findPlaceByCounty(Counties counties) {
        placeList = placeRepository.findAllByCounty(counties);
        return placeList;
    }
}
