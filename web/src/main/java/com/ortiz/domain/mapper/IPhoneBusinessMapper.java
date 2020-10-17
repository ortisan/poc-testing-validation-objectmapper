package com.ortiz.domain.mapper;

import com.ortiz.domain.Phone;
import com.ortiz.dto.PhoneDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPhoneBusinessMapper {
    Phone dtoToDomain(PhoneDTO source);
    @InheritInverseConfiguration(name = "dtoToDomain")
    PhoneDTO domainToDto(Phone source);
}
