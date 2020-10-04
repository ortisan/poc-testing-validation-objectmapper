package com.ortiz.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class PersonDTO {
    @Null
    private String tenantId;
    @Null
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    @Size(min = 11, max = 14)
    private String cpf_cnpj;
}
