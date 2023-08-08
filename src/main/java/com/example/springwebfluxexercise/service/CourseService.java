package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.course.CreateOrUpdateCourseDto;
import reactor.core.publisher.Mono;

public interface CourseService {

    Mono<CourseDto> save(CreateOrUpdateCourseDto courseDto);

    Mono<CourseDto> findById(Long id);

    Mono<CourseDto> updateById(Long id, CreateOrUpdateCourseDto courseDto);
}
