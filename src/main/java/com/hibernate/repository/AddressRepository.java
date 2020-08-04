package com.hibernate.repository;

import com.hibernate.entity.AddressEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {
    @Query(
            value = "SELECT * FROM ADDRESS  WHERE apt_no = 2 ",
            nativeQuery = true)
    Collection<AddressEntity> findApartment();
}