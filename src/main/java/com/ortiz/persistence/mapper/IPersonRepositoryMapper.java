package com.ortiz.persistence.mapper;


import com.ortiz.domain.CorporatePerson;
import com.ortiz.domain.PhysicalPerson;
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
    PhisicalPerson phisicalPersonDomainToEntity(PhysicalPerson phisicalPerson);

    @Mapping(source = "cnpj", target = "cnpj")
    Corporate corporateDomainToEntity(CorporatePerson corporate);

    @InheritInverseConfiguration(name = "phisicalPersonDomainToEntity")
    PhysicalPerson entityToDomain(PhisicalPerson corporate);

    @InheritInverseConfiguration(name = "corporateDomainToEntity")
    CorporatePerson entityToDomain(Corporate corporate);

    default com.ortiz.domain.Person mapToDomain(Person person) {
        if (person instanceof PhisicalPerson) {
            return this.entityToDomain((PhisicalPerson) person);
        }
        return this.entityToDomain((Corporate) person);
    }

    default Person mapToEntity(com.ortiz.domain.Person person) {
        if (person instanceof PhysicalPerson) {
            return this.phisicalPersonDomainToEntity((PhysicalPerson) person);
        }
        return this.corporateDomainToEntity((CorporatePerson) person);
    }
}
