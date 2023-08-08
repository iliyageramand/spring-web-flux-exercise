package com.example.springwebfluxexercise.mapper;

import com.example.springwebfluxexercise.domain.Course;
import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.course.GetCourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toCourse(CourseDto courseDto);

    Course toCourse(GetCourseDto courseDto);

    CourseDto toCourseDto(Course course);

    @Mapping(target="instructor", source="")
    GetCourseDto toGetCourseDto(Course course);
}
