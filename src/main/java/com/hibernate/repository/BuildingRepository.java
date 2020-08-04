package com.hibernate.repository;

import com.hibernate.entity.BuildingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import java.util.UUID;


@Repository
public interface BuildingRepository extends CrudRepository<BuildingEntity, UUID> {

    Collection<BuildingEntity> findAll();



}

