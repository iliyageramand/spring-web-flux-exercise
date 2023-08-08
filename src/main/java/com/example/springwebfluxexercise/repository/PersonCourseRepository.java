package com.example.springwebfluxexercise.repository;

import com.example.springwebfluxexercise.domain.PersonCourse;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonCourseRepository extends ReactiveCrudRepository<PersonCourse, Long> {
}
