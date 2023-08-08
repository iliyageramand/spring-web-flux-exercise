package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.domain.Person;
import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.course.GetCourseDto;
import com.example.springwebfluxexercise.exception.NotFoundException;
import com.example.springwebfluxexercise.mapper.CourseMapper;
import com.example.springwebfluxexercise.mapper.PersonMapper;
import com.example.springwebfluxexercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private static final String NOT_FOUND_MSG = "Course not found";
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final PersonMapper personMapper;
    private final PersonService personService;

    @Override
    public Mono<CourseDto> save(CourseDto courseDto) {
        return courseRepository.save(courseMapper.toCourse(courseDto))
                .map(courseMapper::toCourseDto);
    }

    @Override
    public Mono<GetCourseDto> findById(Long id) {
        return courseRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(NOT_FOUND_MSG)))
                .map(courseMapper::toGetCourseDto)
                .flatMap(courseDto ->
                        personService.findById(courseDto.getInstructorId())
                        .map(personMapper::toPerson)
                        .zipWith(Mono.just(courseDto))
                        .flatMap(objects -> {
                            Person person = objects.getT1();
                            GetCourseDto cDto = objects.getT2();
                            cDto.setInstructor(person);
                            return Mono.just(cDto);
                        }));
    }

    @Override
    public Mono<CourseDto> updateById(Long id, CourseDto courseDto) {
        return courseRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(NOT_FOUND_MSG)))
                .flatMap(course -> {
                    course.setTitle(courseDto.getTitle());
                    return courseRepository.save(course);
                })
                .map(courseMapper::toCourseDto);
    }
}
