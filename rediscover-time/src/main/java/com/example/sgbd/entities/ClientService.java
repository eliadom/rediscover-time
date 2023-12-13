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
    public void addClientQueue(String clientName, Queue queue){
        Client client = new Client();
        client.setQueue(queue);
        client.setName(clientName);
       //  List<Client> allQueues
    }




}
