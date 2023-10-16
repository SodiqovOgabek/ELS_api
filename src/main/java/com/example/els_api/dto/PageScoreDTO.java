package com.example.els_api.dto;

import com.example.els_api.domains.AuthUser;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public interface PageScoreDTO {
    Integer getUserId();

    String getFullName();

    String getEmail();

    String getPhone();

    String getPhoto();

    Integer getTotalScore();
}
