package com.synergenhealth.cdms.redisapp;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class IDGenerator {

    public Long generatedId(){
        long val =  new Random().nextLong();
        return Math.max(val, val*-1);
    }
}
