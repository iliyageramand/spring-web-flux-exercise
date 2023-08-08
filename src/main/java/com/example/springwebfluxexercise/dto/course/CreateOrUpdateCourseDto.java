package com.example.springwebfluxexercise.dto.course;

import com.example.springwebfluxexercise.dto.BaseEntityDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class CreateOrUpdateCourseDto extends BaseEntityDto {

    @Null
    private Long instructorId;

    @Max(50)
    private String title;

    public CreateOrUpdateCourseDto(@Null Long id, @Null Integer version, @Null Timestamp createdDate,
                                   @Null Timestamp lastModifiedDate, Long instructorId, String title) {
        super(id, version, createdDate, lastModifiedDate);
        this.instructorId = instructorId;
        this.title = title;
    }
}
