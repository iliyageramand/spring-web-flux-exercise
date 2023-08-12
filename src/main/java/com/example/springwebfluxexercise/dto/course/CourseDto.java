package com.example.springwebfluxexercise.dto.course;

import com.example.springwebfluxexercise.dto.person.PersonDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    @Size(max=50)
    @NotBlank
    private String title;

    @Valid
    private PersonDto instructor;
}
