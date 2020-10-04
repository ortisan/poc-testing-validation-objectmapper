package com.ortiz.persistence.mapper;


import com.ortiz.domain.mapper.IPersonTypeEnumMapper;
import com.ortiz.persistence.entities.Corporate;
import com.ortiz.persistence.entities.Person;
import com.ortiz.persistence.entities.PhisicalPerson;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = CentralConfig.class, uses = {IPersonTypeEnumMapper.class})
public interface IPersonRepositoryMapper {

    @Mapping(source = "cpf", target = "cpf")
    PhisicalPerson phisicalPersonDomainToEntity(com.ortiz.domain.PhisicalPerson phisicalPerson);

    @Mapping(source = "cnpj", target = "cnpj")
    Corporate corporateDomainToEntity(com.ortiz.domain.Corporate corporate);

    @InheritInverseConfiguration(name = "phisicalPersonDomainToEntity")
    com.ortiz.domain.PhisicalPerson entityToDomain(PhisicalPerson corporate);

    @InheritInverseConfiguration(name = "corporateDomainToEntity")
    com.ortiz.domain.Corporate entityToDomain(Corporate corporate);

    default com.ortiz.domain.Person mapToDomain(Person person) {
        if (person instanceof PhisicalPerson) {
            return this.entityToDomain((PhisicalPerson) person);
        }
        return this.entityToDomain((Corporate) person);
    }

    default Person mapToEntity(com.ortiz.domain.Person person) {
        if (person instanceof com.ortiz.domain.PhisicalPerson) {
            return this.phisicalPersonDomainToEntity((com.ortiz.domain.PhisicalPerson) person);
        }
        return this.corporateDomainToEntity((com.ortiz.domain.Corporate) person);
    }
}
