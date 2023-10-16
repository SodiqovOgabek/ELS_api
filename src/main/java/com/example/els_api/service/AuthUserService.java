package com.example.els_api.service;

import com.example.els_api.domains.AuthUser;
import com.example.els_api.dto.AuthUserCreatedDTO;
import com.example.els_api.dto.LoginDTO;
import com.example.els_api.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final AuthUserRepository authUserRepository;

    public Long login(AuthUserCreatedDTO dto) {

        Optional<AuthUser> byEmail = authUserRepository.getByEmail(dto.getEmail());

        if (byEmail.isEmpty()) {
            AuthUser authUser = AuthUser.builder()
                    .email(dto.getEmail())
                    .fullName(dto.getFullName())
                    .phone(dto.getPhone())
                    .photo(dto.getPhoto())
                    .state(1)
                    .build();
            return authUserRepository.save(authUser).getId();
        }

        return byEmail.get().getId();
    }

}
