package com.example.springwebfluxexercise.dto.course;

import com.example.springwebfluxexercise.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class GetCourseDto extends CourseDto {
    private Person instructor;

    public GetCourseDto(Long id, Integer version, Timestamp createdDate,Timestamp lastModifiedDate,
                        Long instructorId, String title, Person instructor) {
        super(id, version, createdDate, lastModifiedDate, instructorId, title);
        this.instructor = instructor;
    }
}
