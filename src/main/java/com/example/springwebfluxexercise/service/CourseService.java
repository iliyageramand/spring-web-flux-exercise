package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.course.CreateCourseDto;
import com.example.springwebfluxexercise.dto.course.UpdateCourseDto;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {

    Mono<CourseDto> save(CreateCourseDto courseDto);

    Mono<CourseDto> findById(Long id);

    Flux<CourseDto> findAll();

    Mono<CourseDto> updateById(Long id, UpdateCourseDto courseDto);

    Mono<PersonDto> findInstructorByCourseId(Long id);
}
