package com.example.sgbd.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ComponentScan
@EnableAutoConfiguration
@RequestMapping("api/clients")
public class ClientController {



    @Autowired
    private QueueService queueService;

    // @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")

    @GetMapping("/{name}")
    private Queue getQueuesFrom(@PathVariable("name") String name){
        return queueService.getQueueByName(name);
    }

//    @GetMapping(path="/all")
    @GetMapping("/all")
    private List<Queue> getAllQueues(){

        return queueService.getAllQueues();
    }



}