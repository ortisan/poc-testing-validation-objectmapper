package com.ortiz.persistence.entities;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PersonId implements Serializable {
    //private PersonIdentity personIdentity;
    private String personId;
    private String tentantId;
}
