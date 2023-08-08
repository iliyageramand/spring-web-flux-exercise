package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.PersonDto;
import reactor.core.publisher.Mono;

public interface PersonService {

    Mono<PersonDto> save(PersonDto personDto);

    Mono<PersonDto> findById(Long id);

    Mono<PersonDto> updateById(Long id, PersonDto personDto);
}
