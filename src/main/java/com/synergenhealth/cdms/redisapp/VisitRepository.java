package com.synergenhealth.cdms.redisapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface VisitRepository extends CrudRepository<Visit, String> {

    Collection<Visit> findAllByLicenceKey(int licenceKey);
}
