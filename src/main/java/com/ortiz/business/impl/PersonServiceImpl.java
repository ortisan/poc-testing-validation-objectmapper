package com.ortiz.business.impl;

import com.ortiz.business.IPersonService;
import com.ortiz.domain.Person;
import com.ortiz.domain.mapper.IPersonBusinessMapper;
import com.ortiz.dto.PersonDTO;
import com.ortiz.persistence.repositories.service.IPersonRepository;
import com.ortiz.rules.IPersonInsertRule;
import com.ortiz.rules.IPersonUpdateRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IPersonBusinessMapper personBusinessMapper;

    @Autowired
    private IPersonInsertRule personInsertRule;

    @Autowired
    private IPersonUpdateRule personUpdateRule;

    @Override
    public PersonDTO getPerson(String tenantId, String personId) {
        final Person person = personRepository.getPerson(tenantId, personId);
        return personBusinessMapper.mapToDto(person);
    }

    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {
        final Person person = personBusinessMapper.mapToDomain(personDTO);
        personInsertRule.validate(person);

        final Person personSaved = personRepository.savePerson(person);
        return personBusinessMapper.mapToDto(personSaved);
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDTO) {
        final Person person = personBusinessMapper.mapToDomain(personDTO);
        personInsertRule.validate(person);
        final Person personSaved = personRepository.savePerson(person);
        return personBusinessMapper.mapToDto(personSaved);
    }
}
