package com.example.sgbd.entities;

//import io.netty.util.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.*;
import javax.annotation.PostConstruct;

@Service
public class Global {

    @Autowired
    private QueueService queueService;

    @Autowired
    private ClientService clientService;

    private List<Queue> activeQueues = new ArrayList<>();



    @PostConstruct
    void init(){
        // ...

        initData();
    }


    private void initData(){
//        Queue queue2 = new Queue("Queue4", "adeu");
//        queueService.addQueue(queue2);

        queueService.removeAllEntries();
        clientService.removeAllEntries();

        Vector<Client> rvClients = new Vector<Client>();

        //sets 15 people in queue
        for(int i = 1; i <= 15; i++){
            Client cli = new Client();
            cli.setId("a"+i);
            rvClients.add(cli);
        }

        Vector<Client> norClients = new Vector<Client>();
        Vector<Client> tortClients = new Vector<Client>();
        Vector<Client> gusClients = new Vector<Client>();
        Vector<Client> marClients = new Vector<Client>();

        Queue ratonVacilon = new Queue("Ratón Vacilón",15000,5000,5,10,rvClients);
        Queue noria = new Queue("Noria",10000,5000,30, 15, norClients);
        Queue tortugasNinja = new Queue("Tortugas Ninja",10000,10000,15, 5, tortClients);
        Queue gusanoLoco = new Queue("Gusano Loco",20000,15000,40, 20, gusClients);
        Queue marioLand = new Queue("Mario Land",30000,20000,60, 40, marClients);

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
        Collections.sort(allQueues,new queueComp());

        for (int i = 0; i < allQueues.size(); i++) {
            System.out.println("Ride " + i + ": " + allQueues.get(i).getId() + " " + allQueues.get(i).getTimeForNextTrain()/1000);
        }

        System.out.println("Starting Execution at: " + java.time.LocalTime.now());
        int rate = 1000;
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                List<Queue> updateQueues = new ArrayList<>();

                for(Queue q : activeQueues){

                    if(q.getTimeForNextTrain() - rate <= 0){
                        q.resetTimeForNextTrain();
                        q.takeClientsFromQueue();
                        System.out.println(q.getId()+" finished! " + java.time.LocalTime.now());
                    }
                    else {
                        q.modifyTimeForNextTrain(rate);
                        queueService.updateQueueStatus(q);

                    }
                    updateQueues.add(q);
                }

                Collections.sort(updateQueues,new queueComp());

                for (int i = 0; i < updateQueues.size(); i++) {
                    System.out.println("Ride " + i + ": " + updateQueues.get(i).getId() + " " + updateQueues.get(i).getTimeForNextTrain()/1000);
                }

            };
        };
        t.scheduleAtFixedRate(tt,1000,rate);

    }

    //Adds user to queue X and returns its wait time



}
