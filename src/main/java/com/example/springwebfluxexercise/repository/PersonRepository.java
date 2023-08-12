package com.example.springwebfluxexercise.repository;

import com.example.springwebfluxexercise.domain.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {
    Mono<Boolean> existsByNationalId(String nationalId);
}
