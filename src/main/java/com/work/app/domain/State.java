package com.work.app.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static java.util.Objects.nonNull;

@Data
@NoArgsConstructor
public class State {

    @NotBlank(message = "Field 'initials' is required")
    private String initials;

    public State(String initials) {
        this.initials = initials;
    }

    public void setInitials(String initials) {
        if (nonNull(initials)) {
            this.initials = initials.toUpperCase();
        }
    }
}
