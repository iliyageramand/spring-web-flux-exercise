package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.course.GetCourseDto;
import com.example.springwebfluxexercise.exception.NotFoundException;
import com.example.springwebfluxexercise.mapper.CourseMapper;
import com.example.springwebfluxexercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public Mono<CourseDto> save(CourseDto courseDto) {
        return courseRepository.save(courseMapper.toCourse(courseDto))
                .map(courseMapper::toCourseDto);
    }

    @Override
    public Mono<GetCourseDto> findById(Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toGetCourseDto);
    }

    @Override
    public Mono<CourseDto> updateById(Long id, CourseDto courseDto) {
        return courseRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Course not found")))
                .flatMap(course -> {
                    course.setTitle(courseDto.getTitle());
                    return courseRepository.save(course);
                })
                .map(courseMapper::toCourseDto);
    }
}
