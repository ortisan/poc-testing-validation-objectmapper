package com.ortiz.persistence.entities;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class PhisicalPerson extends Person {
    private String cpf;
}
