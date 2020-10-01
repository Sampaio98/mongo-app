package com.work.app.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Document
public class User {

    @EqualsAndHashCode.Include
    @Id
    private String id;

    @NotBlank(message = "Field 'name' is required")
    private String name;

    @NotNull(message = "Field 'gender' is required")
    private Character gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Field 'birthday' is required")
    private LocalDate birthday;

    @NotNull(message = "Field 'age' is required")
    private Integer age;

    @NotBlank(message = "Field 'city' is required")
    private String city;

}
