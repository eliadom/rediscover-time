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

    @Transactional
    public Client addClientQueue(String clientName, Queue queueof){
        String queue_id = queueof.getId();
//        Optional<Client> potserExisteix = clientRepository.findClientByQueue_IdAndName(queue.getId(), clientName);
        boolean potserExisteix = clientRepository.existsById(clientName);
        if (potserExisteix){
//            throw new ServiceException(String.format("Ja estàs a una cua"));
            return null;
        }
        Client client = new Client();
        client.setQueueof(queueof);
        client.setId(clientName);
        return clientRepository.save(client);
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

    public void removeAllEntries(){
        clientRepository.deleteAll();
    }



}
