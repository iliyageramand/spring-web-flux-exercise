package com.example.springwebfluxexercise.repository;

import com.example.springwebfluxexercise.domain.PersonCourse;
import com.example.springwebfluxexercise.dto.course.CourseDto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PersonCourseRepository extends ReactiveCrudRepository<PersonCourse, Long> {

    Flux<PersonCourse> findPersonCourseByPersonId(Long id);

    Flux<PersonCourse> findPersonCourseByCourseId(Long id);
}
