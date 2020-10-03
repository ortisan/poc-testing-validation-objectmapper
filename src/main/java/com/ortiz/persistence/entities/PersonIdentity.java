package com.ortiz.persistence.entities;

import com.ortiz.domain.DocumentTypeEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class PersonIdentity {
    @Id
    private String id;
    private DocumentTypeEnum documentType;
    private String documentNumber;
    private String countryCode;
}
