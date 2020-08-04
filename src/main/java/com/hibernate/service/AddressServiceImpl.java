package com.hibernate.service;


import com.hibernate.converter.AddressConverter;
import com.hibernate.dto.AdressDto;
import com.hibernate.dto.BuildingDto;
import com.hibernate.entity.AddressEntity;
import com.hibernate.entity.BuildingEntity;
import com.hibernate.exception.ObjNotFoundException;
import com.hibernate.repository.AddressRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);


    @Autowired
    AddressConverter addressConverter;
    @Autowired
    AddressRepository addressRepository;


    @Override
    @Transactional( propagation = Propagation.REQUIRED,rollbackFor = ObjNotFoundException.class)
    @CachePut(cacheNames ="adress")
    public AdressDto saveAddress(@Valid AdressDto address) throws ObjNotFoundException {
        try {
            AdressDto savedAdress=addressConverter.AddressEntityToAddressDto(addressRepository.save(addressConverter.AddressDtoToAddressEntity(address)));
            LOGGER.info("The address saved");
            return savedAdress;

        } catch (Exception e) {
              LOGGER.error("Failed to create record !" ,e);
              throw new ObjNotFoundException("Failed to create record !" );
        }
    }

    @Override
    @Cacheable(cacheNames ="adress")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ObjNotFoundException.class)
    public AdressDto getAddressById(UUID id) throws Exception {
        Optional<AddressEntity> optionalAddressEntity = addressRepository.findById(id);

        try  {
            AdressDto getAddress= addressConverter.AddressEntityToAddressDto(optionalAddressEntity.get());

            Thread.sleep(1000*5);
            LOGGER.info("Fetching address from database");
            return getAddress;

        }catch(Exception e) {
            LOGGER.error("Could not find address with this ID !" );
            throw new ObjNotFoundException("There is no any data with this given ID !" );

        }

    }
    @Override
    public List<AdressDto> getAllAddress() {
        return addressConverter.AddressEntityListToAddressDtoList((List<AddressEntity>) addressRepository.findAll());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ObjNotFoundException.class)
    public List<AdressDto> getApartment() throws ObjNotFoundException {
        List<AdressDto> apartmentList = new ArrayList<AdressDto>();
        try {
            apartmentList = addressConverter.AddressEntityListToAddressDtoList((List<AddressEntity>) addressRepository.findApartment());
            LOGGER.info("Apartment is questioned.");
            return apartmentList;
        } catch (Exception e) {
            LOGGER.error("Failed to find apartment !", e);
            throw new ObjNotFoundException("Failed to find apartment !");

        }
    }

    @Override
    @CacheEvict(cacheNames ="adress")
    public void deleteAddress(UUID id) {
        addressRepository.deleteById(id);
        LOGGER.info("Address is deleted successfully." );

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ObjNotFoundException.class)
    @CachePut(cacheNames ="adress")
    public AdressDto updateAddress(@Valid AdressDto addressDto) throws ObjNotFoundException {
        try {
            AdressDto updatedAddress= addressConverter.AddressEntityToAddressDto(addressRepository.save(addressConverter.AddressDtoToAddressEntity(addressDto)));
            LOGGER.info("Address is updated successfully!" );
            return updatedAddress;

        } catch (Exception e) {
            LOGGER.error("Failed to update record !" ,e);
            throw new ObjNotFoundException("Failed to update record !" );
        }
    }
}
