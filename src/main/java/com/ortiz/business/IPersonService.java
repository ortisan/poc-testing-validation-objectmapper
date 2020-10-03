package com.ortiz.business;

import com.ortiz.dto.PersonDTO;

public interface IPersonService {
    PersonDTO getPerson(String tenantId, String personId);
}
