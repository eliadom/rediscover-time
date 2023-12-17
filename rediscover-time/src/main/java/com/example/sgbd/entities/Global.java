package com.example.sgbd.entities;

//import io.netty.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
public class Global {

    @Autowired
    private QueueService queueService;

    @Autowired
    private ClientService clientService;

    private List<Queue> activeQueues = new ArrayList<>();


    @PostConstruct
    void init() {
        // ...

        initData();
    }


    private void initData() {
//        Queue queue2 = new Queue("Queue4", "adeu");
//        queueService.addQueue(queue2);

        queueService.removeAllEntries();
        clientService.removeAllEntries();

        Vector<Client> rvClients = new Vector<Client>();

        //sets 15 people in queue
        for (int i = 1; i <= 15; i++) {
            Client cli = new Client();
            cli.setId("a" + i);
            rvClients.add(cli);
        }

        Vector<Client> norClients = new Vector<Client>();
        Vector<Client> tortClients = new Vector<Client>();
        Vector<Client> gusClients = new Vector<Client>();
        Vector<Client> marClients = new Vector<Client>();

//        Queue ratonVacilon = new Queue("Ratón Vacilón",15000,5000,5,10,rvClients);
        Queue ratonVacilon = new Queue("Ratón Vacilón", 15000, 5000, 2, rvClients.size(), rvClients, 0);
        Queue noria = new Queue("Noria", 10000, 5000, 3, norClients.size(), norClients, 0);
        Queue tortugasNinja = new Queue("Tortugas Ninja", 10000, 10000, 1, tortClients.size(), tortClients, 0);
        Queue gusanoLoco = new Queue("Gusano Loco", 20000, 15000, 2, gusClients.size(), gusClients, 0);
        Queue marioLand = new Queue("Mario Land", 30000, 20000, 3, marClients.size(), marClients,0 );

        queueService.addQueue(ratonVacilon);
        activeQueues.add(ratonVacilon);
        queueService.addQueue(noria);
        activeQueues.add(noria);
        queueService.addQueue(tortugasNinja);
        activeQueues.add(tortugasNinja);
        queueService.addQueue(gusanoLoco);
        activeQueues.add(gusanoLoco);
        queueService.addQueue(marioLand);
        activeQueues.add(marioLand);

        List<Queue> allQueues = queueService.getAllQueues();
        Collections.sort(allQueues, new queueComp());

        for (int i = 0; i < allQueues.size(); i++) {
            System.out.println("Ride " + i + ": " + allQueues.get(i).getId() + " " + allQueues.get(i).getTimeForNextDeparture() / 1000);
        }

        System.out.println("Starting Execution at: " + java.time.LocalTime.now());
        int rate = 1000;
        Timer t = new Timer();
        List<Queue> updateQueues = queueService.getAllQueues();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                List<Queue> currentQueues = queueService.getAllQueues();
                activeQueues = new ArrayList<>();


                for (Queue q : currentQueues) {

                    if (q.getTimeForNextDeparture() - rate <= 0) {
                        queueService.removeClientsFrom(q);
                        q = queueService.updateQueueStatus(q);

                        System.out.println(q.getId() + " finished! " + java.time.LocalTime.now());
                    } else {
                        q.modifyTimeForNextTrain(rate);
                        q = queueService.updateQueueStatus(q);

                    }
                    activeQueues.add(q);
                }


                Collections.sort(updateQueues, new queueComp());

                for (int i = 0; i < currentQueues.size(); i++) {
                    System.out.println("Ride " + i + ": " + currentQueues.get(i).getId() + " " + currentQueues.get(i).getTimeForNextDeparture() / 1000);
                }

            }

            ;
        };
        t.scheduleAtFixedRate(tt, 1000, rate);

    }

    //Adds user to queue X and returns its wait time


}
