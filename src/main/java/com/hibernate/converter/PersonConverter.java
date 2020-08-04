package com.hibernate.converter;


import com.hibernate.dto.PersonDto;
import com.hibernate.entity.BuildingEntity;
import com.hibernate.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses= {BuildingConverter.class})
public interface PersonConverter  {

    @Mapping(source = "buildingEntity", target = "buildingEntity")

    public PersonEntity DtoToEntity(PersonDto personDto);

    public PersonDto EntityToDto(PersonEntity personEntity);

    public List<PersonEntity> personDtoListToEntityList(List<PersonDto> personDtoList);

    public List<PersonDto> personEntityListToPersonDtoList(List<PersonEntity> personEntityList);
}
