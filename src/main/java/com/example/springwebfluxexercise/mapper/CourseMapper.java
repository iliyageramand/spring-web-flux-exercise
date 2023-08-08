package com.example.springwebfluxexercise.mapper;

import com.example.springwebfluxexercise.domain.Course;
import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.course.CreateOrUpdateCourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toCourse(CreateOrUpdateCourseDto courseDto);

    @Mapping(target="instructor", ignore = true)
    CourseDto toCourseDto(Course course);
}
