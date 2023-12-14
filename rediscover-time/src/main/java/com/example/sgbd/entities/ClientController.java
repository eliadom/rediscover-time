package com.example.sgbd.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
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
    private Client apunta(@RequestBody Client client){
        // aconseguim l'atraccio a la qual ens apuntem
        Queue queue = queueService.getQueueByName(client.getQueueof().getId());
        return clientService.addClientQueue(client.getId(), queue);
    }

//    @GetMapping(path="/all")


    @GetMapping("/{name}")
    private Client getClient(@PathVariable("name") String name){
        return clientService.getQueueByName(name);
    }

}