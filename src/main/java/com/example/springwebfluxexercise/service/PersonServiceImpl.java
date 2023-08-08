package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.person.PersonDto;
import com.example.springwebfluxexercise.exception.NotFoundException;
import com.example.springwebfluxexercise.mapper.PersonMapper;
import com.example.springwebfluxexercise.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public Mono<PersonDto> save(PersonDto personDto) {
        return personRepository.save(personMapper.toPerson(personDto))
                .map(personMapper::toPersonDto);
    }

    @Override
    public Mono<PersonDto> findById(Long id) {
        return personRepository.findById(id)
                .map(personMapper::toPersonDto);
    }

    @Override
    @Transactional
    public Mono<PersonDto> updateById(Long id, PersonDto personDto) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Person not found")))
                .flatMap(fetched -> {
                    fetched.setNationalId(personDto.getNationalId());
                    fetched.setFirstName(personDto.getFirstName());
                    fetched.setLastName(personDto.getLastName());
                    fetched.setBirthdate(personDto.getBirthdate());
                    return personRepository.save(fetched);
                })
                .map(personMapper::toPersonDto);
    }
}