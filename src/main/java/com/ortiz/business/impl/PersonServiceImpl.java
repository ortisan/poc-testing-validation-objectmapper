package com.ortiz.business.impl;

import com.ortiz.business.IPersonService;
import com.ortiz.domain.Person;
import com.ortiz.domain.mapper.IPersonBusinessMapper;
import com.ortiz.dto.PersonDTO;
import com.ortiz.persistence.repositories.service.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IPersonBusinessMapper personBusinessMapper;

    @Override
    public PersonDTO getPerson(String tenantId, String personId) {
        final Person person = personRepository.getPerson(tenantId, personId);
        return personBusinessMapper.mapToDto(person);
    }

    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {
        final Person person = personBusinessMapper.mapToDomain(personDTO);
        if (StringUtils.isEmpty(person.getFullName())) {
            throw new IllegalArgumentException("Fullname required");
        }
        final Person personSaved = personRepository.savePerson(person);
        return personBusinessMapper.mapToDto(personSaved);
    }
}
