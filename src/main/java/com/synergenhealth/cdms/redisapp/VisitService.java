package com.synergenhealth.cdms.redisapp;


import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class VisitService {

    @Autowired
    VisitRepository visitRepository;

    @Cacheable("visits-by-licensekey")
    public Collection<Visit> getAllVisitsByLicenseKey(int licenceKey){

        try{

            TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException e){
            log.error("Error during visits by licensekey");
            throw new RuntimeException(e);
        }

        return visitRepository.findAllByLicenceKey(licenceKey);
    }


}
