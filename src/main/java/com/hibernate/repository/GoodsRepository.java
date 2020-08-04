package com.hibernate.repository;

import com.hibernate.entity.GoodsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;


@Repository
public interface GoodsRepository extends CrudRepository<GoodsEntity, UUID> {

    Collection<GoodsEntity> findAll();

}

