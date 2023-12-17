package com.example.sgbd.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.*;

import javax.persistence.*;
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
    private int timeForNextDeparture;

    private int currentWaitTime;

    //people on ride
    private int capacity;

    //maximum people on ride
    private int maxCapacity;
    //people in queue

    @OneToMany(mappedBy = "queueof", cascade = {CascadeType.ALL})
    @JsonIgnoreProperties("queueof")
    private List<Client> clientsInQueue;

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

    public Queue() {

    }

    public Queue(String name, int estimatedTime, int timeForNextDeparture, int maxCapacity, int capacity, List<Client> clientsInQueue, int currentWaitTime) {
        this.id = name;
        this.estimatedTime = estimatedTime;
        this.timeForNextDeparture = timeForNextDeparture;
        this.maxCapacity = maxCapacity;
        this.capacity = capacity;
        this.clientsInQueue = new ArrayList<>();
        if (clientsInQueue != null)
            this.clientsInQueue.addAll(clientsInQueue);
        this.currentWaitTime = currentWaitTime;
    }

    public String getId() {
        return this.id;
    }


    public int getEstimatedTime() {
        return this.estimatedTime;
    }

    public List<Client> getClientsInQueue() {
        return this.clientsInQueue;
    }

    public void setClientsInQueue(Client cli) {
        this.clientsInQueue.add(cli);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void modifyTimeForNextTrain(int rate) {
        this.timeForNextDeparture -= rate;
    }

//    public int resetTimeForNextTrain() {
//        this.timeForNextTrain = estimatedTime;
//        return this.timeForNextTrain;
//    }

    public int setTimeTillNextDeparture(){
        int mida = (clientsInQueue==null) ? 0 : clientsInQueue.size();
        if (mida > maxCapacity){
            mida = maxCapacity;
        }

        int timeToBoard = (int) (mida * 1.5);
        int timeToDismount = (int) (capacity * 1.5);

        this.timeForNextDeparture = (int) (((timeToBoard + timeToDismount)*1000) + estimatedTime);

        return this.timeForNextDeparture;
    }


    public int setCurrentWaitTime(){
        int ridingTimes = (capacity/maxCapacity)*estimatedTime;

        this.currentWaitTime = this.timeForNextDeparture + ridingTimes;
        return this.currentWaitTime;
    }

    public int getCurrentWaitTime(){
        return this.currentWaitTime;
    }

    public int timeTillNextDeparture(){
        int mida;
        if(clientsInQueue.size() > maxCapacity) mida = maxCapacity;
        else mida = clientsInQueue.size();
        double timeToBoard = mida * 1.5;
        double timeToDismount = capacity * 1.5;

        return (int) (timeToBoard + timeToDismount) * 1000;
    }

    public int currentWaitTime(){
        int mida = (clientsInQueue == null) ? 0 : clientsInQueue.size();
        double timeToBoard = mida * 1.5;
        double timeToDismount = capacity * 1.5;

        return (int) (timeToBoard + timeToDismount) * 1000;
    }


//    public Vector<Client> takeClientsFromQueue(){
//        // numMax sera el numero mes petit entre aquests dos.
//        Vector<Client> aEliminar = new Vector<Client>();
//        int numMax = Math.min(clientsInQueue.size(),this.maxCapacity);
//        for (int i = 0; i < numMax; i++){
//            this.capacity--;
//            aEliminar.add(clientsInQueue.get(0));
//            clientsInQueue.remove(0);
//
//        }
//
//        if(clientsInQueue.size() < maxCapacity){
//            //remove all elements from vector
//            clientsInQueue.clear();
//        }
//        else{
//            //remove maxCapacity elements from vector
//            Vector<Client> auxVector = new Vector<Client>();
//            for(int i = maxCapacity; i < clientsInQueue.size(); i++){
//                auxVector.add(clientsInQueue.get(i));
//            }
//            clientsInQueue.addAll(auxVector);
//        }
//        return aEliminar;
//    }

    public int getTimeForNextDeparture() {
        return this.timeForNextDeparture;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void addClient() {
        this.capacity++;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

class queueComp implements Comparator<Queue> {
    public int compare(Queue a, Queue b) {
        return a.getTimeForNextDeparture() - b.getTimeForNextDeparture();
    }
}