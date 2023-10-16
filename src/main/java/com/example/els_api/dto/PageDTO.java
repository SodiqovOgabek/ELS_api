package com.example.els_api.dto;

import com.example.els_api.domains.AuthUser;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDTO {
    private AuthUser user;
    private Page<PageScoreDTO> lists;
}
