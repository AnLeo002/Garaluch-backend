package com.BackendGaraLunch.service.impl;

import com.BackendGaraLunch.controller.dto.AuthCreateUserRequest;
import com.BackendGaraLunch.controller.dto.AuthUpdateUserRequest;
import com.BackendGaraLunch.controller.dto.UserResponse;
import com.BackendGaraLunch.persistence.RoleEntity;
import com.BackendGaraLunch.persistence.UserEntity;
import com.BackendGaraLunch.repo.RoleRepo;
import com.BackendGaraLunch.repo.UserRepo;
import com.BackendGaraLunch.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<UserResponse> findAll() {
        List<UserResponse> userResponseList = repo.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername(),null, user.getName(), user.getLastName(), user.getEmail(), user.getTel(),user.getRoles()))
                .collect(Collectors.toList());
        return userResponseList;
    }

    @Override
    public UserResponse findUserByUsername(String username) {
        UserResponse userResponse = repo.findUser(username).stream().findFirst()//Con esto podemos generar un mapeo(cambio) del objeto
                .map(user -> new UserResponse(user.getId(),user.getUsername(),user.getPassword(), user.getName(), user.getLastName(), user.getEmail(), user.getTel(),user.getRoles()))
                .orElseThrow();
        return userResponse;
    }

    @Override
    public UserResponse updateUser(AuthUpdateUserRequest userRequest, Long id,Boolean retry) {

        UserEntity user = repo.findById(id).orElseThrow(()->new RuntimeException("El usuario no existe"));

        List<String> roles = userRequest.roles().stream().map(role -> role.getRoleEnum().name()).collect(Collectors.toList());
        Set<RoleEntity> roleEntitySet = roleRepo.findRoleEntitiesByRoleEnumIn(roles).stream().collect(Collectors.toSet());

        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setLastName(userRequest.lastName());
        user.setUsername(userRequest.username());
        user.setTel(userRequest.tel());
        user.setPassword(retry?passwordEncoder.encode(userRequest.password()): userRequest.password());
        user.setRoles(roleEntitySet);

        repo.save(user);

        return new UserResponse(user.getId(),userRequest.username(), userRequest.password(), userRequest.name(), userRequest.lastName(), userRequest.email(), userRequest.tel(), roleEntitySet);
    }

    @Override
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}
