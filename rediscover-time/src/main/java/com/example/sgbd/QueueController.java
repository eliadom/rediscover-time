package com.example.sgbd;

import com.example.sgbd.entities.Queue;
import com.example.sgbd.entities.QueueService;
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
@RequestMapping("api/queues")
public class QueueController {



    @Autowired
    private QueueService queueService;

    // @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")

    @GetMapping("/{name}")
    private Queue getQueue(@PathVariable("name") String name){
        return queueService.getQueueByName(name);
    }

//    @GetMapping(path="/all")
    @GetMapping("/all")
    private List<Queue> getAllQueues(){

        return queueService.getAllQueues();
    }

    @GetMapping(value="/all")
    private List<Queue> getAllQueues2(){

        return queueService.getAllQueues();
    }

}