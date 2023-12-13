package com.example.sgbd.entities;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;


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
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;
    private int positionInQueue;

    @ManyToOne
    private Queue queue;

    public Client() {

    }

    public Client(Queue queue) {
        this.queue = queue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void setPositionInQueue(int num){
        this.positionInQueue = num;
    }


}
