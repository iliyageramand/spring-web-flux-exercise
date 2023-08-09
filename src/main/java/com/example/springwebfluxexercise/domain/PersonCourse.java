package com.example.springwebfluxexercise.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "person_course")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PersonCourse extends BaseObject {

    @Column
    private Long personId;

    @Column
    private Long courseId;

    @Transient
    private Person person;

    @Transient
    private Course course;
}
