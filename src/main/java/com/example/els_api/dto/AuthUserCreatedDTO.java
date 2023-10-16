package com.example.els_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserCreatedDTO {

    private String email;
    private String fullName;
    private String phone;
    private String photo;
}
