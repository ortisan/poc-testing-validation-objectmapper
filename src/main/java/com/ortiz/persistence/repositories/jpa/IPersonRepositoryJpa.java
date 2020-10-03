package com.ortiz.persistence.repositories.jpa;

import com.ortiz.persistence.entities.Person;
import com.ortiz.persistence.entities.PersonId;
import org.springframework.data.repository.Repository;

public interface IPersonRepositoryJpa extends Repository<Person, PersonId> {
}
