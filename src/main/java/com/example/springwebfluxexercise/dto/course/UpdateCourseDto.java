package com.example.springwebfluxexercise.dto.course;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseDto {
    private Long instructorId;

    @Size(max=50)
    private String title;
}
