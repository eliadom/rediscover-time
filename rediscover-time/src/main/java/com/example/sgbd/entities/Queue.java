package com.example.sgbd.entities;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.io.*;
import java.lang.*;
import java.util.*;


@EnableCaching
@ImportAutoConfiguration(classes = {
        CacheAutoConfiguration.class,
        RedisAutoConfiguration.class
})
@RedisHash("Queue")
public class Queue implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Id
    private String id;
    // estimated waiting time in minutes
    private int estimatedTime;
    private int timeForNextTrain;

    private int capacity;

    // should come before any other method.
    // @Cacheable("attributetoreturn")

    // -----------------------------------------------
    //
    // We can use the @CacheEvict annotation to indicate
    // the removal of one or more/all values so that fresh
    // values can be loaded into the cache again:
    //
    // @CacheEvict(value="addresses", allEntries=true)
    // public String getAddress(Class class)
    //
    // -----------------------------------------------
    //
    // With the @CachePut annotation, we can update the
    // content of the cache without interfering with the
    // method execution. That is, the method will always
    // be executed and the result cached:
    //
    // @CachePut(value="addresses")
    // public String getAddress(Customer customer) {...}

    public Queue(){

    }

    public Queue(String name, int estimatedTime, int timeForNextTrain, int capacity){
        this.id = name;
        this.estimatedTime = estimatedTime;
        this.timeForNextTrain = timeForNextTrain;
        this.capacity = capacity;
    }

    public String getId(){
        return this.id;
    }


    public int getEstimatedTime(){
        return this.estimatedTime;
    }
    /*
    public int getEstimatedTimeInMS(){
        return this.estimatedTime*1000;
    }
    public int getTimeForNextTrainInMS(){
        return this.timeForNextTrain*1000;
    }
    */
    public void modifyTimeForNextTrain(int rate){
        this.timeForNextTrain -= rate;
    }
    public void resetTimeForNextTrain(){
        this.timeForNextTrain = estimatedTime;
    }

    public int getTimeForNextTrain(){
        return this.timeForNextTrain;
    }

    public int getCapacity(){ return this.capacity; }

    public void addCapacity(){ this.capacity++; }
}

class queueComp implements Comparator<Queue>{
    public int compare(Queue a, Queue b)
    {
        return a.getTimeForNextTrain() - b.getTimeForNextTrain();
    }
}