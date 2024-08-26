package com.BackendGaraLunch.controller;

import com.BackendGaraLunch.controller.dto.AuthCreateUserRequest;
import com.BackendGaraLunch.controller.dto.AuthLoginRequest;
import com.BackendGaraLunch.controller.dto.AuthResponse;
import com.BackendGaraLunch.controller.dto.AuthUpdateUserRequest;
import com.BackendGaraLunch.persistence.UserEntity;
import com.BackendGaraLunch.repo.UserRepo;
import com.BackendGaraLunch.service.UserDetailsServiceImpl;
import com.BackendGaraLunch.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {
    @Autowired
    private UserRepo repo;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }
    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest userRequest){
        if(repo.findUserEntityByUsername(userRequest.username()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(this.userDetailsService.createUser(userRequest),HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public ResponseEntity findAll(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/find/{username}")
    public ResponseEntity findByUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.findUserByUsername(username),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@RequestBody AuthUpdateUserRequest authUpdateUserRequest, @PathVariable Long id){

        Optional<UserEntity> user = repo.findUserEntityByUsername(authUpdateUserRequest.username());
        if(user.isPresent()){
            if (user.get().getId() == id){
                return ResponseEntity.ok(userService.updateUser(authUpdateUserRequest,user.get().getId(),true));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El nombre de usuario ya se encuentra registrado");
        }
        log.error(authUpdateUserRequest.toString());
        return ResponseEntity.ok(userService.updateUser(authUpdateUserRequest,id,true));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try{
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/sessionUser/{username}")
    public UserEntity getSessionUser(@PathVariable String username){
        return repo.findUserEntityByUsername(username).get();
    }
}
