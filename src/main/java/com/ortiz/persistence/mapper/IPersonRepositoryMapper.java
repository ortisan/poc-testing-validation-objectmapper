package com.ortiz.persistence.mapper;


import com.ortiz.persistence.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IPersonRepositoryMapper {
    @Mapping(source = "fullName", target = "name")
    Person personDomainPersonEntity(com.ortiz.domain.Person person);

    @Mapping(source = "name", target = "fullName")
    com.ortiz.domain.Person personEntityToPersonDomain(Person person);
}
