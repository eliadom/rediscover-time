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

    //people on ride
    private int capacity;

    //maximum people on ride
    private int maxCapacity;
    //people in queue
    private Vector<Client> clientsInQueue;

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

    public Queue(String name, int estimatedTime, int timeForNextTrain, int maxCapacity, int capacity, Vector<Client> clientsInQueue){
        this.id = name;
        this.estimatedTime = estimatedTime;
        this.timeForNextTrain = timeForNextTrain;
        this.maxCapacity = maxCapacity;
        this.capacity = capacity;
        this.clientsInQueue = clientsInQueue;
    }

    public String getId(){
        return this.id;
    }


    public int getEstimatedTime(){
        return this.estimatedTime;
    }

    public Vector<Client> getClientsInQueue(){ return this.clientsInQueue; }

    public void addClientToQueue(Client cli){ clientsInQueue.add(cli); }

    public void modifyTimeForNextTrain(int rate){ this.timeForNextTrain -= rate; }
    public void resetTimeForNextTrain(){
        this.timeForNextTrain = estimatedTime + waitTimeinMS();
    }
    public int waitTimeinMS(){
        double timeToBoard = clientsInQueue.size() * 1.5;
        double timeToDismount = capacity * 1.5;

        return (int)(timeToBoard+timeToDismount)*1000;
    }

    public Vector<Client> takeClientsFromQueue(){
        if(clientsInQueue.size() < maxCapacity){
            //remove all elements from vector
            clientsInQueue = new Vector<Client>();
        }
        else{
            //remove maxCapacity elements from vector
            Vector<Client> auxVector = new Vector<Client>();
            for(int i = maxCapacity; i < clientsInQueue.size(); i++){
                auxVector.add(clientsInQueue.get(i));
            }
            clientsInQueue = auxVector;
        }
        return clientsInQueue;
    }

    public int getTimeForNextTrain(){
        return this.timeForNextTrain;
    }

    public int getCapacity(){ return this.capacity; }

    public void addClient(){ this.capacity++; }

    public int getMaxCapacity() {
        return maxCapacity;
    }

}

class queueComp implements Comparator<Queue>{
    public int compare(Queue a, Queue b)
    {
        return a.getTimeForNextTrain() - b.getTimeForNextTrain();
    }
}