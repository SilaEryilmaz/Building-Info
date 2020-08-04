package com.hibernate.repository;

import com.hibernate.base.entity.BaseEntity;
import com.hibernate.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;


@Repository
public interface PersonRepository extends CrudRepository<PersonEntity,UUID> {

    Collection<PersonEntity> findAll();


}

