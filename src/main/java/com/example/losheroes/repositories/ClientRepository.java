package com.example.losheroes.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.losheroes.models.ClientModel;

@Repository
public interface ClientRepository extends CrudRepository<ClientModel,Long> {
    public abstract ArrayList<ClientModel> findByEmail(String email);
    public abstract ArrayList<ClientModel> findByRut(String rut);
;}
