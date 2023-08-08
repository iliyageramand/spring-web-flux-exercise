package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.course.GetCourseDto;
import reactor.core.publisher.Mono;

public interface CourseService {

    Mono<CourseDto> save(CourseDto courseDto);

    Mono<GetCourseDto> findById(Long id);

    Mono<CourseDto> updateById(Long id, CourseDto courseDto);
}
