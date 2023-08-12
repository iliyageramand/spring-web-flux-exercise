package com.example.springwebfluxexercise.repository;

import com.example.springwebfluxexercise.domain.PersonCourse;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PersonCourseRepository extends ReactiveCrudRepository<PersonCourse, Long> {

    Flux<PersonCourse> findPersonCourseByPersonId(Long id);

    Flux<PersonCourse> findPersonCourseByCourseId(Long id);

    Mono<Boolean> existsByPersonIdAndCourseId(Long personId, Long courseId);
}
