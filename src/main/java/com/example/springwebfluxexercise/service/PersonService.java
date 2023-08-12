package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.person.PersonDto;
import com.example.springwebfluxexercise.dto.person.UpdatePersonDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

    Mono<PersonDto> save(PersonDto personDto);

    Mono<PersonDto> findById(Long id);

    Flux<PersonDto> findAll();

    Mono<PersonDto> updateById(Long id, UpdatePersonDto personDto);
    Mono<PersonDto> partialUpdateById(Long id, UpdatePersonDto personDto);
}
