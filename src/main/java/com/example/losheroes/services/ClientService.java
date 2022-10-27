package com.example.losheroes.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.losheroes.models.ClientModel;
import com.example.losheroes.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public ArrayList<ClientModel> getClients(){
        return ( ArrayList<ClientModel> ) clientRepository.findAll();
    }


    public ClientModel saveClient(ClientModel client){
        if(this.isClientEmailExist(client.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already used");
        }
        if(this.isClientRutExist(client.getRut())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Rut already used");
        }
        return clientRepository.save(client);
    }

    public boolean isClientEmailExist(String email){
        ArrayList<ClientModel> client = this.clientRepository.findByEmail(email);
        if(client.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean isClientRutExist(String rut){
        ArrayList<ClientModel> client = this.clientRepository.findByRut(rut);
        if(client.isEmpty()){
            return false;
        }
        return true;

    }


    public Optional<ClientModel> getById(Long id) throws Exception {
        Optional<ClientModel> client = clientRepository.findById(id);
        if(client.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not found");
        }
        return clientRepository.findById(id);
    }




    public ArrayList<ClientModel>  getByEmail(String email) {
        if(this.isClientEmailExist(email) == false){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not found");
        }
        return clientRepository.findByEmail(email);
    }

    

    public boolean deleteById(Long id) {
        Optional<ClientModel> client = clientRepository.findById(id);
        if(client.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not found");
        }

        try{
            clientRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    } 
}
