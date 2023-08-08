package com.example.springwebfluxexercise.dto.course;

import com.example.springwebfluxexercise.dto.person.PersonDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDto {

    @Max(50)
    private String title;

    @Valid
    private PersonDto instructor;
}
