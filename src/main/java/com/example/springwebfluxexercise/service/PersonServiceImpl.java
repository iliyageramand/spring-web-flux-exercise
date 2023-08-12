package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.person.PersonDto;
import com.example.springwebfluxexercise.dto.person.UpdatePersonDto;
import com.example.springwebfluxexercise.exception.AlreadyExistsException;
import com.example.springwebfluxexercise.exception.NotFoundException;
import com.example.springwebfluxexercise.mapper.PersonMapper;
import com.example.springwebfluxexercise.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private static final String NOT_FOUND_MSG = "Person not found";
    private static final String NATIONAL_ID_ALREADY_EXISTS_MSG = "Person with national id=%s already exists";
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public Mono<PersonDto> save(PersonDto personDto) {
        return personRepository.existsByNationalId(personDto.getNationalId())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new AlreadyExistsException(
                                String.format(NATIONAL_ID_ALREADY_EXISTS_MSG, personDto.getNationalId())
                        ));
                    }
                    return personRepository.save(personMapper.toPerson(personDto));
                })
                .map(personMapper::toPersonDto);
    }

    @Override
    public Mono<PersonDto> findById(Long id) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(NOT_FOUND_MSG)))
                .map(personMapper::toPersonDto);
    }

    @Override
    public Flux<PersonDto> findAll() {
        return personRepository.findAll()
                .map(personMapper::toPersonDto);
    }

    @Override
    public Mono<PersonDto> updateById(Long id, UpdatePersonDto personDto) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(NOT_FOUND_MSG)))
                .flatMap(fetched -> {
                    fetched.setFirstName(personDto.getFirstName());
                    fetched.setLastName(personDto.getLastName());
                    fetched.setBirthdate(personDto.getBirthdate());
                    return personRepository.save(fetched);
                })
                .map(personMapper::toPersonDto);
    }
}
