package com.example.springwebfluxexercise.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;

@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Person extends BaseEntity {

    @Column
    @Size(min = 10, max = 10)
    @NotNull
    private String nationalID;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthdate;

    @Transient
    private Integer age;

    public Integer getAge() {
        LocalDate birthdate = this.getBirthdate();
        if (birthdate == null) {
            return null;
        }
        this.age = Period.between(birthdate, LocalDate.now()).getYears();
        return this.age;
    }
}
