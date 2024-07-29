package com.example.springboot.controllers;

import com.example.springboot.dtos.ClientRecordDto;
import com.example.springboot.models.ClientModel;
import com.example.springboot.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ClientController {

    @Autowired
    ClientRepository clientRepository;
    // injeção de dependências meio que instanciando

    // Metódo Post = receber dados,fazer validações e salvar na base de dados

    // esse productRecordDto @valid para ter as validações ditas no productRecordDto
    // depois você instancia um productModel
    // depois conversão de recordDto para model utilizando um recurso do próprio spring
    // no product Repository tem métodos default e esse productRespository que vai o productModel salvar no banco

    @PostMapping("/clients")
    public ResponseEntity<ClientModel> saveClient(@RequestBody @Valid ClientRecordDto clientRecordDto){

        // Instanciando um Client model
        var clientModel = new ClientModel();
        // fazendo a conversão de Dto para model
        BeanUtils.copyProperties(clientRecordDto , clientModel);
        // salvando o produto
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(clientModel));
    }

    // o retorno desse get vai ser uma Lista de clients
    // Esse era o método original
    // return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());

    @GetMapping("/clients")
    public ResponseEntity<List<ClientModel>> getAllClients(){

        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
    }

    // <Object> pois vamos ter dois tipos de respostas
    // @PathVariable serve para você receber o id e ele ser passado pros parâmetros da função

    @GetMapping("/clients/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value="id")UUID id){

        Optional<ClientModel> client0 = clientRepository.findById(id);
        if(client0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(client0.get());
    }

    // update
    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value="id")UUID id,
                                               @RequestBody @Valid ClientRecordDto clientRecordDto){

        Optional<ClientModel> client0 = clientRepository.findById(id);

        if(client0.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }

        var clientModel = client0.get();
        BeanUtils.copyProperties(clientRecordDto , clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(clientModel));
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value="id")UUID id){

        // Procurando o id na base dados
        Optional<ClientModel> client0 = clientRepository.findById(id);

        if(client0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }

        // utilizando um método da jpa
        clientRepository.delete(client0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully");
    }
}
