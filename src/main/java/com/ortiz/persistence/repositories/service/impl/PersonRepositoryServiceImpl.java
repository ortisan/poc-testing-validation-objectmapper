package com.ortiz.persistence.repositories.service.impl;

import com.ortiz.domain.Person;
import com.ortiz.persistence.mapper.IPersonRepositoryMapper;
import com.ortiz.persistence.repositories.jpa.IPersonRepositoryJpa;
import com.ortiz.persistence.repositories.service.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonRepositoryServiceImpl implements IPersonRepository {

    @Autowired
    private IPersonRepositoryJpa personRepositoryJpa;

    @Autowired
    private IPersonRepositoryMapper personRepositoryMapper;

    @Override
    public Person getPerson(String tenantId, String personId) {
        final com.ortiz.persistence.entities.Person person = new com.ortiz.persistence.entities.Person();
        person.setName("Mama");
        return personRepositoryMapper.personEntityToPersonDomain(person);
    }
}
