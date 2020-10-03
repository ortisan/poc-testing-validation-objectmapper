package com.ortiz.dto;

import lombok.Data;

@Data
public class CorporateDTO extends PersonDTO {
    private String cnpj;
}
