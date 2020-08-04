package com.hibernate.converter;


import com.hibernate.dto.BuildingDto;
import com.hibernate.entity.BuildingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressConverter.class, PersonConverter.class})
public interface BuildingConverter {

    public BuildingEntity BuildingDtoToBuildingEntity(BuildingDto buildingDto);

    public BuildingDto BuildingEntityToBuildingDto(BuildingEntity buildingEntity);

    public List<BuildingEntity> BuildingDtoListToBuildingEntityList(List<BuildingDto> buildingDtoList);

    public List<BuildingDto> BuildingEntityListToBuildingDtoList(List<BuildingEntity> buildingEntityList);

}
