package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import reactor.core.publisher.Flux;

public interface PersonCourseService {
    Flux<CourseDto> findCoursesByPersonId(Long id);
    Flux<PersonDto> findStudentsByCourseId(Long id);
}
