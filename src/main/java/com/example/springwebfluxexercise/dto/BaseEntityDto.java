package com.example.springwebfluxexercise.dto;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseEntityDto {

    @Null
    private Long id;

    @Null
    private Integer version;

    @Null
    private Timestamp createdDate;

    @Null
    private Timestamp lastModifiedDate;
}
