package com.example.els_api.repository;

import com.example.els_api.domains.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    @Query("SELECT a FROM AuthUser a WHERE a.email LIKE :email")
    Optional<AuthUser> getByEmail(@Param("email") String email);
}
