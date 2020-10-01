package com.work.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static java.util.Objects.nonNull;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@Document
public class City {

    @EqualsAndHashCode.Include
    @Id
    private String id;

    @NotBlank(message = "Field 'name' is required")
    private String name;

    @Valid
    @NotNull(message = "Field 'state' is required")
    private State state;

    public City(String name, State state) {
        setName(name);
        this.state = state;
    }

    public void setName(String name) {
        if (nonNull(name)) {
            this.name = name.toUpperCase();
        }
    }

}
