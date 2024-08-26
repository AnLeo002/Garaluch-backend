package com.BackendGaraLunch.controller.dto;

import com.BackendGaraLunch.persistence.RoleEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;

public record AuthUpdateUserRequest(@NotBlank String username,
                                    @NotBlank String password,
                                    @NotBlank String name,
                                    @NotBlank String lastName,
                                    @NotBlank String email,
                                    @NotBlank String tel,
                                    Set<RoleEntity> roles) {
}
