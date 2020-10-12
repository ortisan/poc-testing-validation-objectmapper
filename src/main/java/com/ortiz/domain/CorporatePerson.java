package com.ortiz.domain;

import lombok.Data;

@Data
public class CorporatePerson extends Person {
    private String cnpj;
}
