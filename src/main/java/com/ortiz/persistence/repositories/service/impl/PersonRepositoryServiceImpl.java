package com.ortiz.persistence.repositories.service.impl;

import com.ortiz.domain.Person;
import com.ortiz.persistence.entities.PersonId;
import com.ortiz.persistence.mapper.IPersonRepositoryMapper;
import com.ortiz.persistence.repositories.jpa.IPersonRepositoryJpa;
import com.ortiz.persistence.repositories.service.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonRepositoryServiceImpl implements IPersonRepository {

    @Autowired
    private IPersonRepositoryJpa personRepositoryJpa;

    @Autowired
    private IPersonRepositoryMapper personRepositoryMapper;

    @Override
    public Person getPerson(String tenantId, String personId) {
        final PersonId personIdentifier = new PersonId(tenantId, personId);
        final Optional<com.ortiz.persistence.entities.Person> optionalPerson = personRepositoryJpa.findById(personIdentifier);
        return optionalPerson.map(p -> personRepositoryMapper.mapToDomain(p)).orElseThrow(() -> new EntityNotFoundException("Person not found"));
    }

    @Override
    public Person savePerson(Person person) {
        final com.ortiz.persistence.entities.Person personEntity = personRepositoryMapper.mapToEntity(person);
        personEntity.getPersonId().setPersonId(UUID.randomUUID().toString());
        final com.ortiz.persistence.entities.Person personEntitySaved = personRepositoryJpa.save(personEntity);
        return personRepositoryMapper.mapToDomain(personEntitySaved);
    }
}
