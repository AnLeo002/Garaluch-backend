package com.BackendGaraLunch.repo;

import com.BackendGaraLunch.persistence.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findUserEntityByUsername(String username);
    @Query("SELECT u FROM UserEntity u WHERE username = ?1")
    Optional<UserEntity> findUser(String username);
}
