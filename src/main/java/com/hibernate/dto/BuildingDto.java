package com.hibernate.dto;


import com.hibernate.base.dto.BaseDto;
import com.hibernate.entity.AddressEntity;
import com.hibernate.entity.PersonEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BuildingDto  {

    private UUID id;
  /*  private Date createdDate;
    private Date updatedDate;
    private Integer version;*/
    private String buildingName;
    private int numberOfFloors;
    private int numberOfLivingPeople;
    private AddressEntity addressEntity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public int getNumberOfLivingPeople() {
        return numberOfLivingPeople;
    }

    public void setNumberOfLivingPeople(int numberOfLivingPeople) {
        this.numberOfLivingPeople = numberOfLivingPeople;
    }

  /*  public Date getCreatedDate() {
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
