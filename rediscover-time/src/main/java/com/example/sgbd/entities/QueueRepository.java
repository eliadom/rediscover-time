package com.example.sgbd.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface QueueRepository extends JpaRepository<Queue, String> {
    public Optional<Queue> findById(String name);

    public List<Queue> findAll();


}