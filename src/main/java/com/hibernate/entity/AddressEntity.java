package com.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hibernate.base.dto.BaseDto;
import com.hibernate.base.entity.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "ADDRESS")
public class AddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    /*@Column(name = "CREATED_DATE", nullable = false)
    @CreatedDate
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "UPDATED_DATE", nullable = false)
    @LastModifiedDate
    @UpdateTimestamp
    private Date updatedDate;

    @Version
    @Column(name = "VERSION")
    private Integer version;
*/

    @Column(name = "STREET_NAME")
    private String streetName;
    @Column(name = "APT_NO")
    private int aptNo;
    @Column(name = "APT_NAME")
    private String aptName;
    @Column(name = "FLOOR_NO")
    private int floorNo;
    @Column(name = "CITY_NAME")
    private String cityName;
    @Column(name = "ZIP_CODE")
    private String zipCode;
    @Column(name = "COORDINATS")
    private String coordinats;

    @JsonIgnore
   @OneToOne(mappedBy ="addressEntity", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
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

    /*public Date getCreatedDate() {
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
