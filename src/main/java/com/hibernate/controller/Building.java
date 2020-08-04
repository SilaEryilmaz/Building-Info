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
public interface Building {

    @GetMapping(value = "/getBuilding/{id}", produces = MediaType.APPLICATION_JSON_VALUE, name = "getBuilding")
    BuildingDto getBuilding(@PathVariable UUID id) throws ObjNotFoundException;

    @GetMapping(value = "/getAllBuildings", produces = MediaType.APPLICATION_JSON_VALUE, name = "getAllBuildings")
    List<BuildingDto> getAllBuildings();

    @PostMapping(value = "/save/building", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            name = "saveBuilding")
    ResponseEntity<BuildingDto> saveBuilding(@Valid @RequestBody BuildingDto buildingDto) throws ObjNotFoundException;

    @PutMapping(value = "/update/building", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, name = "updateUser")
    ResponseEntity<BuildingDto> updateBuilding(@Valid @RequestBody BuildingDto buildingDto) throws ObjNotFoundException;

    @DeleteMapping(value = "/delete/building/{id}")
    ResponseEntity<BuildingDto> deleteBuilding(@PathVariable UUID id);

}