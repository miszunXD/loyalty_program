package com.miszunXD.loyalty_program.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCommand(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email) {
}
