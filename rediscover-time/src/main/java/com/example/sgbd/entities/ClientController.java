package com.example.sgbd.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

    @Transactional
    @PostMapping("/apunta")
    Client apunta(@RequestBody Client client){
        Queue queue = queueService.getQueueByName(client.getQueueof().getId());

        Double queueSize = (double)queue.getCapacity();
        Double coasterCap = (double)queue.getMaxCapacity();
        Double waitingCicles = Math.floor(queueSize/coasterCap);
        int timeinqueue = (int) (waitingCicles*queue.getCurrentWaitTime());

        client = clientService.addClientQueue(client.getId(), queue);

        queue = queueService.addClientToQueue(client, queue);


        return client;
    }



//    @GetMapping(path="/all")


    @GetMapping("/{name}")
    private Client getClient(@PathVariable("name") String name){
        return queueService.findClientinQueue(name);
    }

}