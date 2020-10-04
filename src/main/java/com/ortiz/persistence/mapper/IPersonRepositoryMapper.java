package com.ortiz.persistence.mapper;


import com.ortiz.persistence.entities.Corporate;
import com.ortiz.persistence.entities.Person;
import com.ortiz.persistence.entities.PhisicalPerson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IPersonRepositoryMapper {
//    @Mapping(source = "fullName", target = "name")
//    @Mapping(source = "personType", target = "type")
//    Person personDomainToPersonEntity(com.ortiz.domain.Person person);

    //@Mapping(source = "name", target = "fullName")
//    @InheritInverseConfiguration
//    com.ortiz.domain.Person personEntityToPersonDomain(Person person);

    //@InheritConfiguration
    //void carDtoIntoCar(com.ortiz.domain.Person personDomain, @MappingTarget Person personEntity);

    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "personType", target = "type")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "tenantId", target = "personId.tenantId")
    PhisicalPerson domainToEntity(com.ortiz.domain.PhisicalPerson phisicalPerson);

    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "personType", target = "type")
    @Mapping(source = "cnpj", target = "cnpj")
    @Mapping(source = "tenantId", target = "personId.tenantId")
    Corporate domainToEntity(com.ortiz.domain.Corporate corporate);

    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "type", target = "personType")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "personId.tenantId", target = "tenantId")
    @Mapping(source = "personId.personId", target = "personIdentity.id")
    com.ortiz.domain.PhisicalPerson entityToDomain(PhisicalPerson corporate);

    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "type", target = "personType")
    @Mapping(source = "cnpj", target = "cnpj")
    @Mapping(source = "personId.tenantId", target = "tenantId")
    @Mapping(source = "personId.personId", target = "personIdentity.id")
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
