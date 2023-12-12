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
    private int positionInQueue;

    @ManyToOne
    private Queue queue;

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

    public Client() {

    }

    public Client(Queue queue) {
        this.queue = queue;
    }



}
