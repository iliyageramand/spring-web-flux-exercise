package com.example.springwebfluxexercise.controller;

import com.example.springwebfluxexercise.dto.course.CreateOrUpdateCourseDto;
import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/course", produces =MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CourseDto> save(@Valid @RequestBody CreateOrUpdateCourseDto courseDto) {
        return courseService.save(courseDto);
    }

    @GetMapping("/{id}")
    public Mono<CourseDto> findById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @GetMapping
    public Flux<CourseDto> findAll() {
        return courseService.findAll();
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CourseDto> updateById(@PathVariable Long id,
                                                    @Valid @RequestBody CreateOrUpdateCourseDto courseDto) {
        return courseService.updateById(id, courseDto);
    }
}
