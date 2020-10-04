package com.ortiz.domain.mapper;

import com.ortiz.domain.Corporate;
import com.ortiz.domain.Person;
import com.ortiz.domain.PhisicalPerson;
import com.ortiz.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IPersonBusinessMapper {
//    @Mapping(source = "fullName", target = "name")
//    PersonDTO personDomainToPersonDto(Person person);

    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "personType", target = "type")
    @Mapping(source = "cpf", target = "cpf_cnpj")
    @Mapping(source = "personIdentity.id", target = "id")
    PersonDTO domainToDto(PhisicalPerson phisicalPerson);

    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "personType", target = "type")
    @Mapping(source = "cnpj", target = "cpf_cnpj")
    @Mapping(source = "personIdentity.id", target = "id")
    PersonDTO domainToDto(Corporate phisicalPerson);

//    @Mapping(source = "name", target = "fullName")
//    Person personDtoPersonDomain(PersonDTO personDTO);

    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "type", target = "personType")
    @Mapping(source = "cpf_cnpj", target = "cpf")
    PhisicalPerson dtoToPhisicalPersonDomain(PersonDTO personDTO);

    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "type", target = "personType")
    @Mapping(source = "cpf_cnpj", target = "cnpj")
    Corporate dtoToCorporateDomain(PersonDTO personDTO);

    default PersonDTO mapToDto(Person person) {
        if (person instanceof PhisicalPerson) {
            return this.domainToDto((PhisicalPerson) person);
        }
        return this.domainToDto((Corporate) person);
    }

    default Person mapToDomain(PersonDTO person) {
        if ("P".equals(person.getType())) {
            return this.dtoToPhisicalPersonDomain(person);
        }
        return this.dtoToCorporateDomain(person);
    }
}
