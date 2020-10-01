package com.work.app.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserNameDTO {

    @NotBlank(message = "Field 'name' is required")
    private String name;
}
