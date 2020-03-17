package com.projects.travelandshare.repository;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlaceRepository extends CrudRepository<Place, Long> {

 Place findPlaceByGrade(float grade);

 Place findPlaceByType (Types types);

 List<Place>  findAllByCounty(Counties counties);

}
