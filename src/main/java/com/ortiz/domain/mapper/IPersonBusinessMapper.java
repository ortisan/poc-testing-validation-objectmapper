package com.ortiz.domain.mapper;

import com.ortiz.domain.Corporate;
import com.ortiz.domain.Person;
import com.ortiz.domain.PhisicalPerson;
import com.ortiz.dto.PersonDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IPersonTypeEnumMapper.class})
public interface IPersonBusinessMapper {

    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "cpf_cnpj", target = "cpf")
    @Mapping(source = "type", target = "personType")
    @Mapping(source = "id", target = "personIdentity.id")
    PhisicalPerson dtoToPhisicalPersonDomain(PersonDTO source);

    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "cpf_cnpj", target = "cnpj")
    @Mapping(source = "type", target = "personType")
    @Mapping(source = "id", target = "personIdentity.id")
    Corporate dtoToCorporateDomain(PersonDTO source);

    @InheritInverseConfiguration
    PersonDTO domainToDto(PhisicalPerson source);

    @InheritInverseConfiguration
    PersonDTO domainToDto(Corporate source);

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
