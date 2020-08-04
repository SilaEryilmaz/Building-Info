package com.hibernate.controller;

import com.hibernate.dto.PersonDto;
import com.hibernate.exception.ObjNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.UUID;

@Validated
public interface Person {

    @GetMapping(value = "/getPerson/{id}", produces = MediaType.APPLICATION_JSON_VALUE, name = "getPerson")
    PersonDto getPerson(@PathVariable UUID id) throws ObjNotFoundException;

    @GetMapping(value = "/getPeople", produces = MediaType.APPLICATION_JSON_VALUE, name = "getPeople")
    List<PersonDto> getAllPeople();

    @PostMapping(value = "/save/person", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            name = "savePerson")
    ResponseEntity<PersonDto> savePerson(@Valid @RequestBody PersonDto personDto) throws ObjNotFoundException;

    @DeleteMapping(value = "/delete/person/{id}")
    ResponseEntity<PersonDto> deletePerson( @PathVariable UUID id);


}
