package com.ortiz.domain.mapper;

import com.ortiz.domain.Person;
import com.ortiz.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IPersonBusinessMapper {
    @Mapping(source = "fullName", target = "name")
    PersonDTO personDomainToPersonDto(Person person);
    @Mapping(source = "name", target = "fullName")
    Person personDtoToPersonDomain(PersonDTO personDTO);
}
