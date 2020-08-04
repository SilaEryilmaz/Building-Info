package com.hibernate.service;



import com.hibernate.dto.PersonDto;
import com.hibernate.exception.ObjNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonDto savePerson(@Valid PersonDto person) throws ObjNotFoundException;
    PersonDto getPersonById(UUID id) throws ObjNotFoundException;
    List<PersonDto> getPeople();
    void deletePerson(UUID id);
    PersonDto updateGoods(PersonDto personDto) throws ObjNotFoundException;
}
