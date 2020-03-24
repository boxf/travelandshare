package com.projects.travelandshare.repository;


import com.projects.travelandshare.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Interface PlaceRepository
 *
 *Used to define methods of CRUD type other than those present in CrudRepository
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     *Used to find place thanks to the grade
     *@param email of the user in String
     *@return a user
     */
    User findUserByEmail(String email);
}
