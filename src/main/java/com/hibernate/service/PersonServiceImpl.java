package com.hibernate.service;


import com.hibernate.converter.PersonConverter;
import com.hibernate.dto.PersonDto;
import com.hibernate.entity.PersonEntity;
import com.hibernate.exception.ObjNotFoundException;
import com.hibernate.repository.PersonRepository;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);


    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonConverter personConverter;


    @Override
    //@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ObjNotFoundException.class)
    @CachePut(cacheNames = "person")
    public PersonDto savePerson( @Valid PersonDto person) throws ObjNotFoundException {
        try {
            PersonDto savedPerson = personConverter.EntityToDto(personRepository.save(personConverter.DtoToEntity(person)));
            LOGGER.info("The person is saved");
            return savedPerson;

        } catch (Exception e) {
            LOGGER.error("Failed to create record !", e);
            throw new ObjNotFoundException("Failed to create record !");
        }
    }

    @Override
    @Cacheable(cacheNames = "person", key = "#id")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ObjNotFoundException.class)
    public PersonDto getPersonById(UUID id) throws ObjNotFoundException {
        Optional<PersonEntity> optionalPersonEntity = personRepository.findById(id);

        try {
            PersonDto getPerson = personConverter.EntityToDto(optionalPersonEntity.get());

            Thread.sleep(1000 * 5);
            LOGGER.info("Fetching person from database");
            return getPerson;

        } catch (Exception e) {
            LOGGER.error("Could not find person with this ID !");
            throw new ObjNotFoundException("There is no any data with this given ID !");

        }
    }

    @Override
    public List<PersonDto> getPeople() {
        return personConverter.personEntityListToPersonDtoList((List<PersonEntity>) personRepository.findAll());
    }

    @Override
    @CacheEvict(cacheNames = "person", key = "#id")
    public void deletePerson(UUID id) {
        personRepository.deleteById(id);
        LOGGER.info("Person is deleted successfully.");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ObjNotFoundException.class)
    @CachePut(cacheNames = "person", key = "#personDto.id")
    public PersonDto updateGoods(PersonDto personDto) throws ObjNotFoundException {
        try {
            PersonDto updatedPerson = personConverter.EntityToDto(personRepository.save(personConverter.DtoToEntity(personDto)));
            LOGGER.info("Person is updated successfully!");
            return updatedPerson;

        } catch (Exception e) {
            LOGGER.error("Failed to update record !", e);
            throw new ObjNotFoundException("Failed to update record !");
        }
    }
}