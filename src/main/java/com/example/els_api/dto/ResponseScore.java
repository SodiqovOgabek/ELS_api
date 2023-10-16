package com.example.els_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseScore {
    private Integer scoreToday;
    private Integer rating;
    private Integer members;
}
