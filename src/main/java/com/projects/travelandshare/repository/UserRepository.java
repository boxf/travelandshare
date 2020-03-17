package com.projects.travelandshare.repository;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
