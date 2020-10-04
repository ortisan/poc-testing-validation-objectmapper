package com.ortiz.domain.mapper;

import com.ortiz.domain.Corporate;
import com.ortiz.domain.Person;
import com.ortiz.domain.PhisicalPerson;
import com.ortiz.dto.PersonDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = CentralConfig.class, uses = {IPersonTypeEnumMapper.class})
public interface IPersonBusinessMapper {

    @Mapping(source = "cpf_cnpj", target = "cpf")
    PhisicalPerson dtoToPhisicalPersonDomain(PersonDTO source);

    @Mapping(source = "cpf_cnpj", target = "cnpj")
    Corporate dtoToCorporateDomain(PersonDTO source);

    @InheritInverseConfiguration(name = "dtoToPhisicalPersonDomain")
    PersonDTO domainToDto(PhisicalPerson source);

    @InheritInverseConfiguration(name = "dtoToCorporateDomain")
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
