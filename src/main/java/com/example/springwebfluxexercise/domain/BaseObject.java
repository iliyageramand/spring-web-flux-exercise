package com.example.springwebfluxexercise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseObject implements Serializable {

    @Version
    @Column
    private int version;

    @CreatedDate
    @Column
    private Timestamp createdDate;

    @LastModifiedDate
    @Column
    private Timestamp lastModifiedDate;
}
