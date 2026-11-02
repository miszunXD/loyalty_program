package com.miszunXD.loyalty_program.dto;

import java.time.LocalDateTime;

public record UserDto(Long id, String firstName, String lastName, String email, LocalDateTime registeredAt) {
}
