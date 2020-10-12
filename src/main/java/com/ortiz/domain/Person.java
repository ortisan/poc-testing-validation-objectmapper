package com.ortiz.domain;

import com.ortiz.domain.enums.PersonTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public abstract class Person {
    private PersonIdentity personIdentity;
    private PersonTypeEnum personType;
    private String tenantId;
    private String fullName;
    private List<Phone> phones;
}
