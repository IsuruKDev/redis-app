package com.synergenhealth.cdms.redisapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByName(String name);
    Collection<User> findAll();
}
