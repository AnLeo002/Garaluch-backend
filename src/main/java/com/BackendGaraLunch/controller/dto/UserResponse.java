package com.BackendGaraLunch.controller.dto;

import com.BackendGaraLunch.persistence.RoleEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
public record UserResponse(@NotBlank Long id,
                           @NotBlank String username,
                           String password,
                           @NotBlank String name,
                           @NotBlank String lastName,
                           @NotBlank String email,
                           @NotBlank String tel,
                           @Valid Set<RoleEntity> roles) {
}
