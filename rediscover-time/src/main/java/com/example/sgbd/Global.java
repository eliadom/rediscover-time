package com.example.sgbd;

import com.example.sgbd.entities.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sgbd.entities.QueueService;

import javax.annotation.PostConstruct;
import java.util.*;

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
        Queue ratonVacilon = new Queue("Ratón Vacilón",12,2, 50);
        Queue noria = new Queue("Noria",10,5,30);
        Queue tortugasNinja = new Queue("Tortugas Ninja",11,6,15);
        Queue gusanoLoco = new Queue("Gusano Loco",20,10,40);

        queueService.addQueue(ratonVacilon);
        queueService.addQueue(noria);
        queueService.addQueue(tortugasNinja);
        queueService.addQueue(gusanoLoco);

        List<Queue> queueManagement = queueService.getAllQueues();
        Map<Queue,Integer> queueTimes = new HashMap<>();
        int maxTime = 0;
        for (Queue element : queueManagement) {
            int time = element.getEstimatedTime() + element.getTimeForNextTrain() + 10; /*Baremo a tenir en compte per descarregar trens*/
            queueTimes.put(element, time);
            if (time > maxTime) maxTime = time;
        }
        final int[] actualTime = {0};
        final Timer[] timer = {new Timer()};
        int finalMaxTime = maxTime;
        final List<Queue>[] queuesToManage = new List[]{new ArrayList<>()};
        timer[0].scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                queuesToManage[0] = getAllMatchingList(queueTimes, actualTime[0]);
                if(!queuesToManage[0].isEmpty()) {
                    updateQueues(queuesToManage[0]);
                    queuesToManage[0].clear();
                }
                actualTime[0]++;
            }
        }, 0, 1000); // Programa la tarea para ejecutarse cada segundo


//        Queue found = queueService.getQueueById("Queue4");
//        System.out.println("FOUND IS: "+ found.getId() + " WITH TEST: " + found.getTest());

    }

    public List<Queue> getAllMatchingList(Map<Queue,Integer> map, int timeToResolve){

        List<Queue> list = new ArrayList<>();
        for (Map.Entry<Queue, Integer> entry : map.entrySet()) {
            if((entry.getValue() % timeToResolve) == 0) list.add(entry.getKey());
        }
        return list;
    }

    public void updateQueues(List<Queue> update){
        for(Queue object: update){
            //cada object fa referencia a una atraccio a la q hem de modificar la cua
        }
    }

    //Adds user to queue X and returns its wait time
    public int addToQueue(Queue add){
        add.addCapacity();
        int actualCapacity = add.getCapacity();
        /*Coaster co = getCoasterForQueue(add);
        Double cap = (double) actualCapacity;
        Double coasterCap = co.getCapacity();
        Double waitingIterations = Math.floor(cap/coasterCap);
        return waitintIterations * (add.getEstimatedTime() + add.getTimeForNextTrain() + 10)
         */
        return 0;
    }

}
