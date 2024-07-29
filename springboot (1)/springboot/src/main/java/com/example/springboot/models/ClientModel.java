package com.example.springboot.models;


import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "TB_CLIENTS") // nome da tabela
public class ClientModel extends RepresentationModel<ClientModel> implements Serializable {
    // RepresentationModel facilita a adição de hateos ou seja aqueles links que retornam o status do cliente

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idClient;
    private String name;
    private String endereco;
    private String email;
    private String senha;

    public UUID getIdClient() {
        return this.idClient;
    }

    public void setIdClient(UUID idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
