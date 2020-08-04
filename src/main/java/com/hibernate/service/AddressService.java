package com.hibernate.service;


import com.hibernate.dto.AdressDto;
import com.hibernate.dto.BuildingDto;
import com.hibernate.exception.ObjNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface AddressService {

    AdressDto saveAddress(@Valid AdressDto address) throws ObjNotFoundException;
    AdressDto getAddressById(UUID id) throws Exception;
    List<AdressDto> getAllAddress();
    void deleteAddress(UUID id);
    AdressDto updateAddress(AdressDto addressDto) throws ObjNotFoundException;
    List<AdressDto> getApartment() throws ObjNotFoundException;

}
