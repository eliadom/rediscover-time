package com.example.sgbd.entities;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;


    @Autowired
    QueueService queueService;

    @Transactional
    public Client addClientQueue(String clientName, Queue queueof){
        String queue_id = queueof.getId();
//        Optional<Client> potserExisteix = clientRepository.findClientByQueue_IdAndName(queue.getId(), clientName);
        boolean potserExisteix = clientRepository.existsById(clientName);
        if (potserExisteix){
            return null;
        }
        Client client = new Client();
        client.setId(clientName);
        return client;
    }


    public Client getQueueByName(String name){
        Optional<Client> clientOptional = clientRepository.findById(name);
        if (clientOptional.isPresent()){
            return clientOptional.get();
        }
        else{
            throw new ServiceException(String.format("Client with name %s does not exist",name));
        }
    }

    @Transactional
    public void removeAllEntries(){
        clientRepository.deleteAll();
    }

    @Transactional
    public void removeClient(Client c){
        clientRepository.delete(c);
    }

    @Transactional
    public void removeClients(List<Client> clientList){
        clientRepository.deleteAll(clientList);

    }





}
