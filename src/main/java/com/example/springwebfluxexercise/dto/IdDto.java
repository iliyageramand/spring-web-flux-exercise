package com.example.springwebfluxexercise.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdDto {

    @NotNull
    @JsonAlias({"course_id", "student_id", "instructor_id"})
    private Long id;
}
