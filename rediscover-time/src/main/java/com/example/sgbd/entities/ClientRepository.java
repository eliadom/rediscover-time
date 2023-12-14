package com.example.sgbd.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    // aquesta crida ens retorna totes les queues a les que
    // s'ha apuntat un client en concret.
    public List<Client> findAllByName(String name);

    // aquesta crida ens retorna totes les queues corresponents
    // a una atracció en concret.



}