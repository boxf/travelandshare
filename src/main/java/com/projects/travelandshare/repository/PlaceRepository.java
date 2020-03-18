package com.projects.travelandshare.repository;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interface PlaceRepository
 *
 *Used to define methods of CRUD type other than those present in CrudRepository
 */
@Repository
public interface PlaceRepository extends CrudRepository<Place, Long> {

 /**
 Used to find place thanks to the grade
  @param grade grade of the place in float
  @return a place
  */
 Place findPlaceByGrade(float grade);

 /**
  * Used to find place thanks to the type
  * @param types : enumeration of types
  * @return a place
  */
 Place findPlaceByType (Types types);

 /**
  * Used to find place thanks to the county
  * @param counties : enumeration of counties
  * @return a list of places
  */
 List<Place>  findAllByCounty(Counties counties);

}
