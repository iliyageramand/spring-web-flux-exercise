package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.person.CreateOrUpdatePersonDto;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

    Mono<PersonDto> save(CreateOrUpdatePersonDto personDto);

    Mono<PersonDto> findById(Long id);

    Flux<PersonDto> findAll();

    Mono<PersonDto> updateById(Long id, CreateOrUpdatePersonDto personDto);
}
