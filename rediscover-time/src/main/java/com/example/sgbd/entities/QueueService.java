package com.example.sgbd.entities;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class QueueService {

    @Autowired
    QueueRepository queueRepository;

    @Transactional
    public void addQueue(Queue queue){
        queueRepository.save(queue);
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

    public void removeAllEntries(){
        queueRepository.deleteAll();
    }

    public List<Queue> getAllQueues(){
        List<Queue> queueList = queueRepository.findAll();
        return queueList;
    }
}
