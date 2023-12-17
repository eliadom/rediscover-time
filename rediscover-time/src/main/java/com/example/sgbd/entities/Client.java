package com.example.sgbd.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;


@EnableCaching
@ImportAutoConfiguration(classes = {
        CacheAutoConfiguration.class,
        RedisAutoConfiguration.class
})
@RedisHash("Client")
public class Client implements Serializable {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Id
    private String id;

    private int positionInQueue;

    private int estimatedTimeinQueue;

    private String date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("clientsInQueue")
    private Queue queueof;

    public Client() {

    }

    public Client(Queue queue) {
        this.queueof = queue;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setQueueof(Queue queue) {
        this.queueof = queue;
    }

    public Queue getQueueof(){
        return  this.queueof;
    }

    public void setPositionInQueue(int num){
        this.positionInQueue = num;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }
    public int getPositionInQueue(){
        return this.positionInQueue;
    }

    public int getEstimatedTimeinQueue() {
        return estimatedTimeinQueue;
    }

    public void setEstimatedTimeinQueue(int estimatedTimeinQueue) {
        this.estimatedTimeinQueue = estimatedTimeinQueue;
    }


}
