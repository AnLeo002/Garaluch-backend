package com.BackendGaraLunch.service;

import com.BackendGaraLunch.controller.dto.AuthCreateUserRequest;
import com.BackendGaraLunch.controller.dto.AuthLoginRequest;
import com.BackendGaraLunch.controller.dto.AuthResponse;
import com.BackendGaraLunch.persistence.RoleEntity;
import com.BackendGaraLunch.persistence.UserEntity;
import com.BackendGaraLunch.repo.RoleRepo;
import com.BackendGaraLunch.repo.UserRepo;
import com.BackendGaraLunch.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo repo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = repo.findUserEntityByUsername(username)
                .orElseThrow(()->new RuntimeException("The user " + username + " do not exist"));

        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        //Tomamos los roles y los transformamos a un lenguaje que entienda spring security
        user.getRoles()
                .forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        //Tomamos los permisos que se encuntran dentro de los roles y los transformamos a un lenguaje que entienda spring security
        user.getRoles().stream()
                .flatMap(role-> role.getPermissionEntities().stream())
                .forEach(permissionEntity -> grantedAuthorities.add(new SimpleGrantedAuthority(permissionEntity.getName())));
        return new User(user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNoExpired(),
                user.isCredentialNoExpired(),
                user.isAccountNoLocked(),
                grantedAuthorities);
    }
    public AuthResponse loginUser (AuthLoginRequest authLoginRequest){

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(username,"User logged successfully",accessToken,true);
        return authResponse;
    }
    public Authentication authenticate(String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails==null){
            throw new BadCredentialsException("Invalid username or password");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());
    }
    public AuthResponse createUser(AuthCreateUserRequest userRequest){
        String username = userRequest.username();
        String password = userRequest.password();

        List<String> roleRequest = userRequest.roleRequest().roleListName();
        log.info(userRequest.toString());
        Set<RoleEntity> roleEntitySet = roleRepo.findRoleEntitiesByRoleEnumIn(roleRequest).stream().collect(Collectors.toSet());
        log.info(roleEntitySet.toString());
        if (roleEntitySet.isEmpty()){
            throw new IllegalArgumentException("The roles specified do not exist");
        }
        UserEntity user = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(userRequest.name())
                .lastName(userRequest.lastName())
                .tel(userRequest.tel())
                .email(userRequest.email())
                .roles(roleEntitySet)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        UserEntity userCreated = repo.save(user);

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreated.getRoles().forEach(role->authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        userCreated.getRoles().stream().flatMap(role->role.getPermissionEntities().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getUsername(),userCreated.getPassword(),authorityList);

        String token = jwtUtils.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(userCreated.getUsername(),"User created successfully",token,true);
        return authResponse;
    }
}
