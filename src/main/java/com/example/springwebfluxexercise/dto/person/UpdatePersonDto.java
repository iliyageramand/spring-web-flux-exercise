package com.example.springwebfluxexercise.dto.person;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePersonDto {
    @Size(max=50)
    private String firstName;

    @Size(max=50)
    private String lastName;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate birthdate;
}
