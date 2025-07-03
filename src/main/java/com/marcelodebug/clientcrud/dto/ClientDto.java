package com.marcelodebug.clientcrud.dto;

import com.marcelodebug.clientcrud.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ClientDto {
    private Long id;
    @NotBlank(message = "O nome não pode estar em branco")
    private String name;
    private String cpf;
    private Double income;
    @PastOrPresent(message = "Data de nascimento não pode ser futura")
    private LocalDate birthDate;
    private Integer children;

    public ClientDto(){}

    public ClientDto(Client client){
        children = client.getChildren();
        birthDate = client.getBirthDate();
        income = client.getIncome();
        cpf = client.getCpf();
        name = client.getName();
        id = client.getId();
    }

    public ClientDto(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public Long getId() {
        return id;
    }
}

