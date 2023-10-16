package com.example.els_api.domains;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp start_date;
    private Timestamp end_date;
    private Integer score;
    @ManyToOne
    private AuthUser user;
    private Integer state;
    private Timestamp created_at = Timestamp.valueOf(LocalDateTime.now());
}
