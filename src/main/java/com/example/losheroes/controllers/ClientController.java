package com.example.losheroes.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.losheroes.models.ClientModel;
import com.example.losheroes.services.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping()
    public ArrayList<ClientModel> getClients(){
        return clientService.getClients();
    }

    @PostMapping()
    public ClientModel saveClient(@RequestBody ClientModel client){
        return this.clientService.saveClient(client);
    }

    @GetMapping(path = "/{id}")
    public Optional<ClientModel> getById(@PathVariable("id") Long id)throws Exception{
        return this.clientService.getById(id);
    }

    @GetMapping("/query")
    public ArrayList<ClientModel> getByEmail(@RequestParam("email") String email){
        return this.clientService.getByEmail(email);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.clientService.deleteById(id);

        if (ok){
            return "client removed successfully id: " + id;
        }else{
            return "No pudo eliminar el usuario con id" + id;
        }
    }


}