package com.projects.travelandshare.service;

import com.projects.travelandshare.model.dto.CreatePlaceDTO;
import com.projects.travelandshare.model.entity.Place;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PlaceDTOService {
    public Place placeDTOCopyPlaceEntity(CreatePlaceDTO placeDTO){
        Place newPlaceEntity = new Place();
        if(placeDTO.getPictureFile() != null) {
            newPlaceEntity.setPictureName(placeDTO.getPictureFile().getOriginalFilename());
        }
        BeanUtils.copyProperties(placeDTO, newPlaceEntity);
        return newPlaceEntity;
    }
}
