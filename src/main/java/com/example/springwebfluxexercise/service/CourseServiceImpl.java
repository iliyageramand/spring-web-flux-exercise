package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.course.CreateOrUpdateCourseDto;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import com.example.springwebfluxexercise.exception.NotFoundException;
import com.example.springwebfluxexercise.mapper.CourseMapper;
import com.example.springwebfluxexercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private static final String NOT_FOUND_MSG = "Course not found";
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final PersonService personService;

    @Override
    public Mono<CourseDto> save(CreateOrUpdateCourseDto courseDto) {
        return courseRepository.save(courseMapper.toCourse(courseDto))
                .map(courseMapper::toCourseDto);
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
                        }));
    }

    @Override
    public Flux<CourseDto> findAll() {
        return courseRepository.findAll()
                .map(courseMapper::toCourseDto);
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
}
