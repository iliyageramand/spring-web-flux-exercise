package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.IdDto;
import com.example.springwebfluxexercise.dto.PersonCourseDto;
import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonCourseService {
    Flux<CourseDto> findCoursesByPersonId(Long id);

    Flux<PersonDto> findStudentsByCourseId(Long id);

    Mono<PersonCourseDto> takeCourse(Long studentId, IdDto courseId);
}
