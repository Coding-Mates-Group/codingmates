package com.gbc.codingmates.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class LoginDTO {
    @NotNull
    private String username;

    @NotNull
    private String password;

}
