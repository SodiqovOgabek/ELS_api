package com.example.els_api.controller;

import com.example.els_api.domains.Score;
import com.example.els_api.dto.*;
import com.example.els_api.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/score")
public class ScoreController {
    private final ScoreService scoreService;

    @PostMapping("")
    public ResponseEntity<ResponseScore> score(@RequestBody ScoreDTO dto) {
        return new ResponseEntity<>(scoreService.score(dto), HttpStatus.CREATED);
    }

    //
    @GetMapping("/all")
    public PageDTO getAllProducts(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestBody ScoreDateDTO dto) {
        Pageable pageable = PageRequest.of(page, limit);
        return scoreService.findAll(pageable, userId, dto);
    }
}
