package com.ortiz.domain.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IPersonTypeEnumMapper.class})
public interface IEnumMapper {
}
