package com.BackendGaraLunch.service;

import com.BackendGaraLunch.controller.dto.AuthCreateUserRequest;
import com.BackendGaraLunch.controller.dto.AuthUpdateUserRequest;
import com.BackendGaraLunch.controller.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse findUserByUsername(String username);
    UserResponse updateUser(AuthUpdateUserRequest userRequest, Long id,Boolean retry);
    void deleteUser(Long id);
}
