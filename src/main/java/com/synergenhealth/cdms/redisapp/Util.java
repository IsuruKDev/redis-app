package com.synergenhealth.cdms.redisapp;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Util {

    public Long generatedId(){
        long val =  new Random().nextLong();
        return Math.max(val, val*-1);
    }

    public long measure(Runnable r){
        long start = System.currentTimeMillis();
        r.run();
        long stop = System.currentTimeMillis();

        return stop-start;
    }
}
