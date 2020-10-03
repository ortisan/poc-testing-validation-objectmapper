package com.ortiz.web;

import com.ortiz.business.IPersonService;
import com.ortiz.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping("/tentant/{tenant_id}/person/{person_id}")
    private PersonDTO getPerson(@PathVariable(name = "tenant_id") String tenantId, @PathVariable(name = "person_id") String personId) {
        return personService.getPerson(tenantId, personId);
    }
}
