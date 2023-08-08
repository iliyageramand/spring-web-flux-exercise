package com.example.springwebfluxexercise.domain;

import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Course extends BaseEntity {

    @Column
    private Long instructorId;

    @Column
    @Max(50)
    private String title;
}
