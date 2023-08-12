package com.example.springwebfluxexercise.mapper;

import com.example.springwebfluxexercise.domain.Person;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target="age", ignore = true)
    Person toPerson(PersonDto personDto);

    PersonDto toPersonDto(Person person);
}
