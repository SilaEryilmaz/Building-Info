package com.hibernate.dto;


import com.hibernate.base.dto.BaseDto;
import com.hibernate.base.entity.BaseEntity;
import com.hibernate.entity.BuildingEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public class AdressDto  {

    private UUID id;
  /*  private Date createdDate;
    private Date updatedDate;
    private Integer version;*/
    private String streetName;
    private int aptNo;
    private String aptName;
    private int floorNo;
    private String cityName;
    private String zipCode;
    private String coordinats;
    private BuildingEntity buildingEntity;


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public BuildingEntity getBuildingEntity() {
        return buildingEntity;
    }

    public void setBuildingEntity(BuildingEntity buildingEntity) {
        this.buildingEntity = buildingEntity;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getAptNo() {
        return aptNo;
    }

    public void setAptNo(int aptNo) {
        this.aptNo = aptNo;
    }

    public String getAptName() {
        return aptName;
    }

    public void setAptName(String aptName) {
        this.aptName = aptName;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCoordinats() {
        return coordinats;
    }

    public void setCoordinats(String coordinats) {
        this.coordinats = coordinats;
    }

   /* public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }*/
}
