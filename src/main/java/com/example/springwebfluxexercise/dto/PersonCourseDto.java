package com.example.springwebfluxexercise.dto;

import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonCourseDto {

    @Valid
    private CourseDto course;

    @Valid
    private PersonDto student;
}
