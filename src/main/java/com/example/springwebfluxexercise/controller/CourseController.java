package com.example.springwebfluxexercise.controller;

import com.example.springwebfluxexercise.dto.IdDto;
import com.example.springwebfluxexercise.dto.PersonCourseDto;
import com.example.springwebfluxexercise.dto.course.CreateCourseDto;
import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.course.UpdateCourseDto;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import com.example.springwebfluxexercise.service.CourseService;
import com.example.springwebfluxexercise.service.PersonCourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/course", produces =MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final PersonCourseService personCourseService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CourseDto> save(@Valid @RequestBody CreateCourseDto courseDto) {
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

    @GetMapping("/{id}/instructor")
    public Mono<PersonDto> findInstructorByCourseId(@PathVariable Long id) {
        return courseService.findInstructorByCourseId(id);
    }

    @GetMapping("/{id}/students")
    public Flux<PersonDto> findStudentsByCourseId(@PathVariable Long id) {
        return personCourseService.findStudentsByCourseId(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CourseDto> updateById(@PathVariable Long id,
                                      @Valid @RequestBody UpdateCourseDto courseDto) {
        return courseService.updateById(id, courseDto);
    }

    @PostMapping("/{id}/addStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PersonCourseDto> addStudent(@PathVariable Long id,
                                            @Valid @RequestBody IdDto studentId) {
        return personCourseService.takeCourse(studentId.getId(), new IdDto(id));
    }
}
