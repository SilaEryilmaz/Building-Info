package com.hibernate.controller;


import com.hibernate.dto.GoodsDto;
import com.hibernate.dto.PersonDto;
import com.hibernate.exception.ObjNotFoundException;
import com.hibernate.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/person")
public class PersonControllerImpl implements Person {

    public static final Logger LOGGER = LoggerFactory.getLogger(PersonControllerImpl.class);


    @Autowired
    PersonService personService;


    @Override
    public PersonDto getPerson(@PathVariable UUID id) throws ObjNotFoundException {
        LOGGER.debug("The person with id number" + id + " has been questioned.");

        return personService.getPersonById(id);

    }

    @Override
    public List<PersonDto> getAllPeople() {
        LOGGER.debug("All people queried.");

        return personService.getPeople();
    }

    @Override
    public ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto personDto) throws ObjNotFoundException {
        PersonDto savedNewDto = personService.savePerson(personDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedNewDto.getId())
                .toUri();

        LOGGER.debug("New person has been added to the system.");

        return ResponseEntity.created(uri)
                .body(savedNewDto);
    }

    public ResponseEntity<PersonDto> updateGoods(@Valid PersonDto personDto) throws ObjNotFoundException {

        PersonDto savedPersonDto = personService.updateGoods(personDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPersonDto)
                .toUri();
        LOGGER.debug("The person updated.");
        return ResponseEntity.created(location).body(savedPersonDto);
    }

    @Override
    public ResponseEntity<PersonDto> deletePerson( UUID id) {
        personService.deletePerson(id);

        LOGGER.debug("The person deleted.");

        return ResponseEntity.noContent().build();
    }
}
