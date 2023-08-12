package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.course.CreateOrUpdateCourseDto;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import com.example.springwebfluxexercise.exception.AlreadyExistsException;
import com.example.springwebfluxexercise.exception.NotFoundException;
import com.example.springwebfluxexercise.mapper.CourseMapper;
import com.example.springwebfluxexercise.mapper.PersonMapper;
import com.example.springwebfluxexercise.repository.CourseRepository;
import com.example.springwebfluxexercise.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private static final String NOT_FOUND_MSG = "Course not found";
    private final CourseRepository courseRepository;
    private final PersonRepository personRepository;
    private final CourseMapper courseMapper;
    private final PersonMapper personMapper;
    private final PersonService personService;

    @Override
    public Mono<CourseDto> save(CreateOrUpdateCourseDto courseDto) {
        String title = courseDto.getTitle();
        Long instructorId = courseDto.getInstructorId();

        return courseRepository.existsByTitleAndInstructorId(title, instructorId)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new AlreadyExistsException(
                                String.format("Course already has an instructor with id=%s", instructorId))
                        );
                    }
                    if (instructorId != null) {
                        return personRepository.findById(instructorId)
                                .switchIfEmpty(Mono.error(new NotFoundException(
                                        String.format("No person found with id=%s", instructorId)
                                )))
                                .flatMap(person -> courseRepository.save(courseMapper.toCourse(courseDto))
                                        .map(courseMapper::toCourseDto)
                                        .map(dto -> {
                                            dto.setInstructor(personMapper.toPersonDto(person));
                                            return dto;
                                        }));
                    }
                    return courseRepository.save(courseMapper.toCourse(courseDto))
                            .map(courseMapper::toCourseDto);
                });
    }

    @Override
    public Mono<CourseDto> findById(Long id) {
        return courseRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(NOT_FOUND_MSG)))
                .flatMap(course ->
                        personService.findById(course.getInstructorId())
                        .zipWith(Mono.just(course))
                        .flatMap(objects -> {
                            PersonDto personDto = objects.getT1();
                            CourseDto cDto = courseMapper.toCourseDto(objects.getT2());
                            cDto.setInstructor(personDto);
                            return Mono.just(cDto);
                        })
                );
    }

    @Override
    public Flux<CourseDto> findAll() {
        return courseRepository.findAll()
                .flatMap(course ->
                        personService.findById(course.getInstructorId())
                        .zipWith(Mono.just(course))
                        .flatMap(objects -> {
                            PersonDto personDto = objects.getT1();
                            CourseDto cDto = courseMapper.toCourseDto(objects.getT2());
                            cDto.setInstructor(personDto);
                            return Mono.just(cDto);
                        })
                );
    }

    @Override
    public Mono<CourseDto> updateById(Long id, CreateOrUpdateCourseDto courseDto) {
        return courseRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(NOT_FOUND_MSG)))
                .flatMap(course -> {
                    course.setTitle(courseDto.getTitle());
                    return courseRepository.save(course);
                })
                .map(courseMapper::toCourseDto);
    }

    @Override
    public Mono<PersonDto> findInstructorByCourseId(Long id) {
        return courseRepository.findInstructorByCourseId(id)
                .map(personMapper::toPersonDto);
    }
}
