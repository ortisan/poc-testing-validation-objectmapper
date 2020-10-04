package com.ortiz.domain;

import com.ortiz.domain.enums.PersonTypeEnum;
import lombok.Data;

@Data
public abstract class Person {
    private PersonIdentity personIdentity;
    //private PersonTypeEnum personType;
    private String personType;
    private String tenantId;
    private String fullName;
}
