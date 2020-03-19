package com.projects.travelandshare.service;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.repository.PlaceRepository;
import com.projects.travelandshare.service.exception.ConflictException;
import com.projects.travelandshare.util.Counties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private PlaceRepository placeRepository;
    @Autowired
    private StorageService storageService;
    List<Place> placeList;


    public Place registerPlace(Place place) {
        if (placeRepository.findPlaceByName(place.getName()) == null){
            this.placeRepository.save(place);
        return place;}
        else {
    public void registerPlace(Place place) {
        if(placeRepository.findPlaceByName(place.getName()) == null)
            this.placeRepository.save(place);
        else{
            throw new ConflictException();

        }
    }

    public Place registerPlace(Place place, MultipartFile file) {
        if (placeRepository.findPlaceByName(place.getName()) == null) {
            this.storageService.savePicture(file);
            place.setPictureName(file.getOriginalFilename());
            this.placeRepository.save(place);
            return place;
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
    /**
     * Used to find a list of places thanks to theirs counties in the dataBase
     * @param counties: enumeration of counties
     * @return a list of places
     * @author Dambrine François
     */
    public List<Place> findPlaceByCounty(Counties counties){
        List<Place> placeList = placeRepository.findAllByCounty(counties);
        return placeList;
    }
    /**
     * Used to find all the places which are in the dataBase
     * @return a list of all the places
     * @author Dambrine François
     */
    public List<Place> findAllPlace (){
        List<Place> placeList = (List<Place>) placeRepository.findAll();
        return placeList;
    }
}
