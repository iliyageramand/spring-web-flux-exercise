package com.example.springwebfluxexercise.controller;

import com.example.springwebfluxexercise.dto.person.CreateOrUpdatePersonDto;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import com.example.springwebfluxexercise.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonDto> save(@Valid @RequestBody CreateOrUpdatePersonDto personDto) {
        return personService.save(personDto);
    }

    @GetMapping("/{id}")
    public Mono<PersonDto> findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonDto> updateById(@PathVariable Long id,
                                      @Valid @RequestBody CreateOrUpdatePersonDto personDto) {
        return personService.updateById(id, personDto);
    }
}
