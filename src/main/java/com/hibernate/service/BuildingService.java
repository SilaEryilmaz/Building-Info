package com.hibernate.service;


import com.hibernate.dto.BuildingDto;
import com.hibernate.exception.ObjNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface BuildingService {
    BuildingDto saveBuilding(@Valid BuildingDto building) throws ObjNotFoundException;
    BuildingDto getBuildingById(UUID id) throws ObjNotFoundException;
    List<BuildingDto> getAllBuilding();
    void deleteBuilding(UUID id);
    BuildingDto updateBuilding(BuildingDto buildingDto) throws ObjNotFoundException;
}
