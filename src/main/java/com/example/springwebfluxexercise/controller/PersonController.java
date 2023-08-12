package com.example.springwebfluxexercise.controller;

import com.example.springwebfluxexercise.dto.IdDto;
import com.example.springwebfluxexercise.dto.PersonCourseDto;
import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import com.example.springwebfluxexercise.dto.person.UpdatePersonDto;
import com.example.springwebfluxexercise.service.PersonCourseService;
import com.example.springwebfluxexercise.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping(path = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final PersonCourseService personCourseService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PersonDto> save(@Valid @RequestBody PersonDto personDto) {
        return personService.save(personDto);
    }

    @GetMapping("/{id}")
    public Mono<PersonDto> findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @GetMapping
    public Flux<PersonDto> findAll() {
        return personService.findAll();
    }

    @GetMapping("{id}/courses")
    public Flux<CourseDto> findCoursesByPersonId(@PathVariable Long id) {
        return personCourseService.findCoursesByPersonId(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonDto> updateById(@PathVariable Long id,
                                      @Valid @RequestBody UpdatePersonDto personDto) {
        return personService.updateById(id, personDto);
    }

    @PatchMapping("/{id}")
    public Mono<PersonDto> partialUpdateById(@PathVariable Long id,
                                             @Valid @RequestBody UpdatePersonDto personDto) {
        return personService.partialUpdateById(id, personDto);
    }

    @PostMapping("/{id}/takeCourse")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PersonCourseDto> takeCourse(@PathVariable Long id,
                                            @Valid @RequestBody IdDto courseId) {
        return personCourseService.takeCourse(id, courseId);
    }
}
