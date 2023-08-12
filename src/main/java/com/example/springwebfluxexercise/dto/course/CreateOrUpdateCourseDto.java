package com.example.springwebfluxexercise.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateCourseDto {
    private Long instructorId;

    @Size(max=50)
    @NotNull
    @NotBlank
    private String title;
}
