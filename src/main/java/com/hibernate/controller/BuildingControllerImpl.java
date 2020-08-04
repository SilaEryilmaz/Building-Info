package com.hibernate.controller;

import com.hibernate.dto.AdressDto;
import com.hibernate.dto.BuildingDto;
import com.hibernate.exception.ObjNotFoundException;
import com.hibernate.service.BuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(path = "/building")
public class BuildingControllerImpl implements Building {

    public static final Logger LOGGER = LoggerFactory.getLogger(BuildingControllerImpl.class);

    @Autowired
    BuildingService buildingService;

    @Override
    public BuildingDto getBuilding(@PathVariable UUID id) throws ObjNotFoundException {
        LOGGER.debug("The building with id number" + id + " has been questioned.");

        return buildingService.getBuildingById(id);

    }

    @Override
    public List<BuildingDto> getAllBuildings() {

        LOGGER.debug("All buildings queried." );

        return buildingService.getAllBuilding();
    }


    @Override
    public ResponseEntity<BuildingDto> saveBuilding(@RequestBody BuildingDto buildingDto) throws ObjNotFoundException {
        BuildingDto savedNewDto = buildingService.saveBuilding(buildingDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedNewDto.getId())
                .toUri();

        LOGGER.debug("New building has been added to the system.");

        return ResponseEntity.created(uri).body(savedNewDto);
    }

    @Override
    public ResponseEntity<BuildingDto> updateBuilding(@Valid BuildingDto buildingDto) throws ObjNotFoundException {

        BuildingDto savedBuildingDto = buildingService.updateBuilding(buildingDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBuildingDto)
                .toUri();
        LOGGER.debug("Building updated.");
        return ResponseEntity.created(location).body(savedBuildingDto);
    }

    @Override
    public ResponseEntity<BuildingDto> deleteBuilding(UUID id) {
        buildingService.deleteBuilding(id);

        LOGGER.debug("Building has been deleted." );
        return ResponseEntity.noContent().build();
    }
}
