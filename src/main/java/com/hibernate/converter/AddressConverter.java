package com.hibernate.converter;


import com.hibernate.dto.AdressDto;
import com.hibernate.dto.BuildingDto;
import com.hibernate.entity.AddressEntity;
import com.hibernate.entity.BuildingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses={BuildingConverter.class})
public interface AddressConverter {

    @Mapping(source="buildingEntity", target="buildingEntity")
    public AddressEntity AddressDtoToAddressEntity(AdressDto addressDto);

    public AdressDto AddressEntityToAddressDto(AddressEntity addressEntity);

    public List<AddressEntity> AddressDtoListToAddressEntityList(List<AdressDto> addressDtoList);

    public List<AdressDto> AddressEntityListToAddressDtoList(List<AddressEntity> addressEntityList);
}
