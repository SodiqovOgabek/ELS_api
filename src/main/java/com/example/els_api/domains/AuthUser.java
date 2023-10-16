package com.example.els_api.domains;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Builder.Default
    private Timestamp created_at = Timestamp.valueOf(LocalDateTime.now());
    @Column(unique = true, nullable = false)
    private String email;
    private String fullName;
    private String phone;
    private String photo;
    private Integer state;
}