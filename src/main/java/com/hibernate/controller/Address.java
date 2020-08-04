package com.hibernate.controller;


import com.hibernate.dto.AdressDto;
import com.hibernate.dto.BuildingDto;
import com.hibernate.exception.ObjNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.UUID;

@Validated
public interface Address {

    @GetMapping(value = "/getAddress/{id}", produces = MediaType.APPLICATION_JSON_VALUE, name = "getAddress")
    AdressDto getAddress(@PathVariable UUID id) throws Exception;

    @GetMapping(value = "/getAllAddress", produces = MediaType.APPLICATION_JSON_VALUE, name = "getAllAddress")
    List<AdressDto> getAllAddress();

    @GetMapping(value = "/getApartment", produces = MediaType.APPLICATION_JSON_VALUE, name = "getApartment")
    List<AdressDto> getApartment() throws ObjNotFoundException;

    @PostMapping(value = "/saveAddress", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            name = "saveAddress")
    ResponseEntity<AdressDto> saveAddress(@Valid @RequestBody AdressDto addressDto) throws ObjNotFoundException;

    @PutMapping(value = "/update/address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, name = "updateUser")
    ResponseEntity<AdressDto> updateAddress(@Valid @RequestBody AdressDto addressDto) throws ObjNotFoundException;

    @DeleteMapping(value = "/delete/address/{id}")
    ResponseEntity<AdressDto> deleteAddress(@PathVariable UUID id);
}



