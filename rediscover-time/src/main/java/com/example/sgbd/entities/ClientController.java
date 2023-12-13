package com.example.sgbd.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan
@EnableAutoConfiguration
@RequestMapping("api/clients")
public class ClientController {



    @Autowired
    private QueueService queueService;

    @Autowired
    private ClientService clientService;

    // @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")

    @PostMapping("/apunta")
    private Queue apunta(@RequestBody String client, @RequestBody String atraccio){
        // aconseguim l'atraccio a la qual ens apuntem
        Queue queue = queueService.getQueueByName(atraccio);
        clientService.addClientQueue(client, queue);
        return null; // test;
    }

//    @GetMapping(path="/all")




}