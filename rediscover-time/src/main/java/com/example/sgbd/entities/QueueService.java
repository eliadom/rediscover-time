package com.example.sgbd.entities;

import org.apache.tomcat.jni.Time;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.*;

@Service
public class QueueService {

    @Autowired
    QueueRepository queueRepository;

    @Transactional
    public Queue addQueue(Queue queue){
        return queueRepository.save(queue);
    }



    public Queue getQueueByName(String name){
        Optional<Queue> queueOptional = queueRepository.findById(name);
        if (queueOptional.isPresent()){
            return queueOptional.get();
        }
        else{
            throw new ServiceException(String.format("Queue with name %s does not exist",name));
        }
    }

    @Transactional
    public void removeAllEntries(){
        queueRepository.deleteAll();
    }

    @Transactional
    public void removeQueue(Queue queue){
        queueRepository.delete(queue);
    }



    public List<Queue> getAllQueues(){
        List<Queue> queueList = queueRepository.findAll();
        return queueList;
    }

    @Transactional
    public Queue updateQueueStatus(Queue q){
        queueRepository.delete(q);
        int waitTime = q.setCurrentWaitTime();
        q = new Queue(q.getId(),q.getEstimatedTime(),q.getTimeForNextDeparture(),q.getMaxCapacity(),q.getCapacity(),q.getClientsInQueue(), waitTime);
        return queueRepository.save(q);
    }

    @Transactional
    public Queue addClientToQueue(Client c, Queue q){

        removeQueue(q);

        List<Client> clientList = q.getClientsInQueue();
        if (clientList == null)
            clientList = new ArrayList<>();
        int currentWaitTime = q.getCurrentWaitTime();


        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        minute += (currentWaitTime/60000);
        if (minute >= 60){
            int addHours = minute/60;
            minute %= 60;
            hour += addHours;
        }


        c.setPositionInQueue(clientList.size()+1);

        String minutesString = Integer.toString(minute);
        if (minute<10){
            minutesString = "0" + minutesString;
        }
        c.setDate(Integer.toString(hour)+":"+minutesString);

        clientList.add(c);


        q.setCurrentWaitTime();
        Queue newq = new Queue(q.getId(),q.getEstimatedTime(), q.getTimeForNextDeparture(), q.getMaxCapacity(),clientList.size(),clientList, q.getCurrentWaitTime());

        newq = addQueue(newq);

        return newq;

    }

    @Transactional
    public Queue removeClientsFrom(Queue q){
        int capacity = q.getCapacity();
        // numMax sera el numero mes petit entre aquests dos.
        List<Client> clientsInQueue = q.getClientsInQueue();
        int clientsSize =  (clientsInQueue==null) ? 0 : clientsInQueue.size();
        if (clientsSize != 0) {
            int numMax = Math.min(clientsSize, q.getMaxCapacity());
            for (int i = 0; i < numMax; i++) {
//                capacity--;
                clientsInQueue.remove(0);
            }
            capacity=clientsInQueue.size();

            q.setCapacity(capacity);

            for (int i = 0; i < clientsInQueue.size(); i++) {
                Client cli = clientsInQueue.get(i);
                cli.setPositionInQueue(cli.getPositionInQueue() - 1);
            }
        }
        q.setTimeTillNextDeparture();
        q.setCurrentWaitTime();
        return q;
    }

    public Client findClientinQueue(String id){
        List<Queue> queueList = queueRepository.findAll();
        boolean clientFound = false;
        int i = 0;
        int j = 0;
        while(!clientFound && i < queueList.size()){
            Queue aux = queueList.get(i);
            j = 0;
            if(aux.getClientsInQueue() != null){
                while(!clientFound && j < aux.getClientsInQueue().size()){
                    if(id.equals(aux.getClientsInQueue().get(j).getId())) clientFound = true;
                    else j++;
                }
            }
            if(!clientFound) i++;
        }

        if (!clientFound){
            return null;
        }

        Client thisClient = queueList.get(i).getClientsInQueue().get(j);
        Queue queue =  queueList.get(i);
        thisClient.setQueueof(queue);
        return thisClient;
    }
}
