package com.example.springwebfluxexercise.dto.person;

import com.example.springwebfluxexercise.dto.BaseEntityDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class CreateOrUpdatePersonDto extends BaseEntityDto {
    @Size(min = 10, max = 10)
    @NotNull
    private String nationalId;

    @Max(50)
    private String firstName;

    @Max(50)
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthdate;

    public CreateOrUpdatePersonDto(@Null Long id, @Null Integer version, @Null Timestamp createdDate,
                                   @Null Timestamp lastModifiedDate, String nationalId, String firstName,
                                   String lastName, LocalDate birthdate) {
        super(id, version, createdDate, lastModifiedDate);
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }
}
