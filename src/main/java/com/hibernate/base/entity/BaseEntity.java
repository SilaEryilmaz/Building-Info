package com.hibernate.base.entity;

import com.hibernate.controller.Goods;
import com.hibernate.entity.AddressEntity;
import com.hibernate.entity.BuildingEntity;
import com.hibernate.entity.GoodsEntity;
import com.hibernate.entity.PersonEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseEntity implements Serializable {

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;*/

    @Column(name = "CREATED_DATE", nullable = false)
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


  /*  public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public Date getCreatedDate() {
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
    }


}
