package com.ortiz.persistence.mapper;


import com.ortiz.domain.mapper.IPersonTypeEnumMapper;
import com.ortiz.persistence.entities.Corporate;
import com.ortiz.persistence.entities.Person;
import com.ortiz.persistence.entities.PhisicalPerson;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IPersonTypeEnumMapper.class})
public interface IPersonRepositoryMapper {

    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "personType", target = "type")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "tenantId", target = "personId.tenantId")
    @Mapping(source = "personIdentity.id", target = "personId.personId")
    PhisicalPerson domainToEntity(com.ortiz.domain.PhisicalPerson phisicalPerson);

    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "personType", target = "type")
    @Mapping(source = "cnpj", target = "cnpj")
    @Mapping(source = "tenantId", target = "personId.tenantId")
    @Mapping(source = "personIdentity.id", target = "personId.personId")
    Corporate domainToEntity(com.ortiz.domain.Corporate corporate);

    @InheritInverseConfiguration
    com.ortiz.domain.PhisicalPerson entityToDomain(PhisicalPerson corporate);

    @InheritInverseConfiguration
    com.ortiz.domain.Corporate entityToDomain(Corporate corporate);


    default com.ortiz.domain.Person mapToDomain(Person person) {
        if (person instanceof PhisicalPerson) {
            return this.entityToDomain((PhisicalPerson) person);
        }
        return this.entityToDomain((Corporate) person);
    }

    default Person mapToEntity(com.ortiz.domain.Person person) {
        if (person instanceof com.ortiz.domain.PhisicalPerson) {
            return this.domainToEntity((com.ortiz.domain.PhisicalPerson) person);
        }
        return this.domainToEntity((com.ortiz.domain.Corporate) person);
    }
}
