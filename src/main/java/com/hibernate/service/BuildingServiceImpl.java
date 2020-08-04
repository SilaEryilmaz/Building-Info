package com.hibernate.service;


import com.hibernate.controller.Building;
import com.hibernate.converter.BuildingConverter;
import com.hibernate.dto.BuildingDto;
import com.hibernate.entity.BuildingEntity;
import com.hibernate.exception.ObjNotFoundException;
import com.hibernate.repository.BuildingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.*;

@Service
public class BuildingServiceImpl implements BuildingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuildingServiceImpl.class);

    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    BuildingConverter buildingConverter;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ObjNotFoundException.class)
    @CachePut(cacheNames = "building")
    public BuildingDto saveBuilding(@Valid BuildingDto building) throws ObjNotFoundException {
        try {
            BuildingDto savedBuilding = buildingConverter.BuildingEntityToBuildingDto(buildingRepository.save(buildingConverter.BuildingDtoToBuildingEntity(building)));

            LOGGER.info("The building is saved");
            return savedBuilding;

        } catch (Exception e) {
            LOGGER.error("Failed to create record !", e);
            throw new ObjNotFoundException("Failed to create record !");
        }
    }

    @Override
    @Cacheable(cacheNames = "building")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ObjNotFoundException.class)
    public BuildingDto getBuildingById(UUID id) throws ObjNotFoundException {
        Optional<BuildingEntity> optionalBuildingEntity = buildingRepository.findById(id);

        try {
            BuildingDto getBuilding = buildingConverter.BuildingEntityToBuildingDto(optionalBuildingEntity.get());

            Thread.sleep(1000 * 5);
            LOGGER.info("Fetching building from database");
            return getBuilding;

        } catch (Exception e) {
            LOGGER.error("Could not find building with this ID !");
            throw new ObjNotFoundException("There is no any data with this given ID !");

        }
    }

    @Override
    public List<BuildingDto> getAllBuilding() {
        return buildingConverter.BuildingEntityListToBuildingDtoList((List<BuildingEntity>) buildingRepository.findAll());

    }

    @Override
    @CacheEvict(cacheNames = "building")
    public void deleteBuilding(UUID id) {
        buildingRepository.deleteById(id);
        LOGGER.info("Building is deleted successfully.");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ObjNotFoundException.class)
    @CachePut(cacheNames = "building", key = "#buildingDto.id")
    public BuildingDto updateBuilding(BuildingDto buildingDto) throws ObjNotFoundException {
        try {
            BuildingDto updatedBuilding = buildingConverter.BuildingEntityToBuildingDto(buildingRepository.save(buildingConverter.BuildingDtoToBuildingEntity(buildingDto)));
            LOGGER.info("Building is updated successfully!");
            return updatedBuilding;

        } catch (Exception e) {
            LOGGER.error("Failed to update record !", e);
            throw new ObjNotFoundException("Failed to update record !");
        }
    }



}

