package com.example.sgbd;

import com.example.sgbd.entities.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sgbd.entities.QueueService;

import javax.annotation.PostConstruct;

@Service
public class Global {

    @Autowired
    private QueueService queueService;



    @PostConstruct
    void init(){
        // ...

        initData();
    }


    private void initData(){
//        Queue queue2 = new Queue("Queue4", "adeu");
//        queueService.addQueue(queue2);

        queueService.removeAllEntries();
        Queue ratonVacilon = new Queue("Ratón Vacilón",12,2);
        Queue noria = new Queue("Noria",10,5);

        queueService.addQueue(ratonVacilon);
        queueService.addQueue(noria);

//        Queue found = queueService.getQueueById("Queue4");
//        System.out.println("FOUND IS: "+ found.getId() + " WITH TEST: " + found.getTest());

    }
}
