package com.example.springwebfluxexercise.repository;

import com.example.springwebfluxexercise.domain.Course;
import com.example.springwebfluxexercise.domain.Person;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CourseRepository extends ReactiveCrudRepository<Course, Long> {

    @Query("SELECT * FROM person p JOIN course c on p.id = c.instructor_id WHERE c.id = :id")
    Mono<Person> findInstructorByCourseId(@Param("id") Long id);
}
