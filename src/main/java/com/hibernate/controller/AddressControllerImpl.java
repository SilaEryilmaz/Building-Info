package com.hibernate.controller;


import com.hibernate.dto.AdressDto;
import com.hibernate.dto.BuildingDto;
import com.hibernate.exception.ObjNotFoundException;
import com.hibernate.service.AddressService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;

@RestController
@RequestMapping(path = "/address")
public class AddressControllerImpl implements Address {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddressControllerImpl.class);

    @Autowired
    AddressService addressService;

    @Override
    public AdressDto getAddress(@PathVariable UUID id) throws Exception {
        LOGGER.debug("The address with id number" + id + " has been questioned.");

        return addressService.getAddressById(id);


    }

    @Override
    public List<AdressDto> getAllAddress() {
        LOGGER.debug("All addresses queried.");

        return addressService.getAllAddress();
    }

    @Override
    public List<AdressDto> getApartment() throws ObjNotFoundException {

        LOGGER.debug("The apartment with apartment number 2 was questioned." );

        return addressService.getApartment();
    }

    @Override
    public ResponseEntity<AdressDto> saveAddress(@RequestBody AdressDto addressDto) throws ObjNotFoundException {
        LOGGER.debug("New address has been added to the system.");

        AdressDto savedNewDto = addressService.saveAddress(addressDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedNewDto.getId())
                .toUri();


        return ResponseEntity.created(uri).body(savedNewDto);


    }


    @Override
    public ResponseEntity<AdressDto> updateAddress(@Valid AdressDto addressDto) throws ObjNotFoundException {
        AdressDto savedAddressDto = addressService.updateAddress(addressDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAddressDto)
                .toUri();
        LOGGER.debug("Address updated.");
        return ResponseEntity.created(location).body(savedAddressDto);
    }

    @Override
    public ResponseEntity<AdressDto> deleteAddress(UUID id) {
        addressService.deleteAddress(id);

        LOGGER.debug("The user has been deleted.");
        return ResponseEntity.noContent().build();
    }
}
