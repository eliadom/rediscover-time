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



    @PostConstruct
    void init(){
        // ...

        initData();
    }


    private void initData(){
//        Queue queue2 = new Queue("Queue4", "adeu");
//        queueService.addQueue(queue2);

        queueService.removeAllEntries();
        Queue ratonVacilon = new Queue("Ratón Vacilón",15000,5000,50);
        Queue noria = new Queue("Noria",10000,5000,30);
        Queue tortugasNinja = new Queue("Tortugas Ninja",10000,10000,15);
        Queue gusanoLoco = new Queue("Gusano Loco",20000,15000,40);
        Queue marioLand = new Queue("Mario Land",30000,20000,60);

        queueService.addQueue(ratonVacilon);
        queueService.addQueue(noria);
        queueService.addQueue(tortugasNinja);
        queueService.addQueue(gusanoLoco);
        queueService.addQueue(marioLand);

        List<Queue> allQueues = queueService.getAllQueues();
        Collections.sort(allQueues,new queueComp());

        for (int i = 0; i < allQueues.size(); i++) {
            System.out.println("Ride " + i + ": " + allQueues.get(i).getId() + " " + allQueues.get(i).getTimeForNextTrain()/1000);
        }

        System.out.println("Starting Execution at: " + java.time.LocalTime.now());
        int rate = 5000;
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                List<Queue> updateQueues = new ArrayList<>();

                if(ratonVacilon.getTimeForNextTrain() - rate <= 0){
                    ratonVacilon.resetTimeForNextTrain();
                    System.out.println("Raton Vacilon finished! " + java.time.LocalTime.now());
                }
                else {
                    ratonVacilon.modifyTimeForNextTrain(rate);
                    //System.out.println("Time Remaining for Raton Vacilon: " + ratonVacilon.getTimeForNextTrain()/1000);
                }
                updateQueues.add(ratonVacilon);

                if(noria.getTimeForNextTrain() - rate <= 0){
                    noria.resetTimeForNextTrain();
                    System.out.println("Noria finished! " + java.time.LocalTime.now());
                }
                else {
                    noria.modifyTimeForNextTrain(rate);
                    //System.out.println("Time Remaining for Noria: " + noria.getTimeForNextTrain()/1000);
                }
                updateQueues.add(noria);

                if(tortugasNinja.getTimeForNextTrain() - rate <= 0){
                    tortugasNinja.resetTimeForNextTrain();
                    System.out.println("Tortugas Ninja finished! " + java.time.LocalTime.now());
                }
                else {
                    tortugasNinja.modifyTimeForNextTrain(rate);
                    //System.out.println("Time Remaining for Tortugas Ninja: " + tortugasNinja.getTimeForNextTrain()/1000);
                }
                updateQueues.add(tortugasNinja);

                if(gusanoLoco.getTimeForNextTrain() - rate <= 0){
                    gusanoLoco.resetTimeForNextTrain();
                    System.out.println("Gusano Loco finished! " + java.time.LocalTime.now());
                }
                else {
                    gusanoLoco.modifyTimeForNextTrain(rate);
                    //System.out.println("Time Remaining for Gusano Loco: " + gusanoLoco.getTimeForNextTrain()/1000);
                }
                updateQueues.add(gusanoLoco);

                if(marioLand.getTimeForNextTrain() - rate <= 0){
                    marioLand.resetTimeForNextTrain();
                    System.out.println("Mario Land finished! " + java.time.LocalTime.now());
                }
                else {
                    marioLand.modifyTimeForNextTrain(rate);
                    //System.out.println("Time Remaining for Mario Land: " + marioLand.getTimeForNextTrain()/1000);
                }
                updateQueues.add(marioLand);
                //System.out.println("New time is: " + java.time.LocalTime.now());

                Collections.sort(updateQueues,new queueComp());

                for (int i = 0; i < updateQueues.size(); i++) {
                    System.out.println("Ride " + i + ": " + updateQueues.get(i).getId() + " " + updateQueues.get(i).getTimeForNextTrain()/1000);
                }

            };
        };
        t.scheduleAtFixedRate(tt,1000,rate);

//        ArrayList<Queue> queueManagement = queueService.getAllQueues();
//        Map<Queue,Integer> queueTimes = new HashMap<>();
//        int maxTime = 0;
//        for (Queue element : queueManagement) {
//            int time = element.getEstimatedTime() + element.getTimeForNextTrain() + 10; /*Baremo a tenir en compte per descarregar trens*/
//            queueTimes.put(element, time);
//            if (time > maxTime) maxTime = time;
//        }
//        final int[] actualTime = {0};
//        final Timer[] timer = {new Timer()};
//        int finalMaxTime = maxTime;
//        final List<Queue>[] queuesToManage = new List[]{new ArrayList<>()};
//        timer[0].scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                queuesToManage[0] = getAllMatchingList(queueTimes, actualTime[0]);
//                if(!queuesToManage[0].isEmpty()) {
//                    updateQueues(queuesToManage[0]);
//                    queuesToManage[0].clear();
//                }
//                actualTime[0]++;
//            }
//        }, 0, 1000); // Programa la tarea para ejecutarse cada segundo


//        Queue found = queueService.getQueueById("Queue4");
//        System.out.println("FOUND IS: "+ found.getId() + " WITH TEST: " + found.getTest());

    }

//    public List<Queue> getAllMatchingList(Map<Queue,Integer> map, int timeToResolve){
//
//        List<Queue> list = new ArrayList<>();
//        for (Map.Entry<Queue, Integer> entry : map.entrySet()) {
//            if((entry.getValue() % timeToResolve) == 0) list.add(entry.getKey());
//        }
//        return list;
//    }
//
//    public void updateQueues(List<Queue> update){
//        for(Queue object: update){
//            //cada object fa referencia a una atraccio a la q hem de modificar la cua
//        }
//    }

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
