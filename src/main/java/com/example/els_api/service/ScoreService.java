package com.example.els_api.service;

import com.example.els_api.domains.AuthUser;
import com.example.els_api.domains.Score;
import com.example.els_api.dto.*;
import com.example.els_api.exception.NotFoundException;
import com.example.els_api.repository.AuthUserRepository;
import com.example.els_api.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final AuthUserRepository authUserRepository;

    public ResponseScore score(ScoreDTO dto) {
        Optional<Score> byScore = scoreRepository.findByScore(dto.getUserId(), Date.valueOf(LocalDate.now()));
        Optional<AuthUser> byUser = authUserRepository.findById(dto.getUserId());
        Score savedScore = null;
        if (byScore.isEmpty()) {
            if (byUser.isPresent()) {
                Score score = Score.builder()
                        .start_date(Timestamp.valueOf(LocalDateTime.now()))
                        .state(1)
                        .end_date(Timestamp.valueOf(LocalDateTime.now()))
                        .score(dto.getScore())
                        .user(byUser.get())
                        .build();

                savedScore = scoreRepository.save(score);

            } else {
                throw new NotFoundException("Foydalanuvchi topilmadi");
            }
        } else {
            if (dto.getScore() == -1 && byScore.get().getScore() <= 0) {
                byScore.get().setScore(0);
            } else {
                byScore.get().setScore(byScore.get().getScore() + dto.getScore());
            }
            byScore.get().setEnd_date(Timestamp.valueOf(LocalDateTime.now()));
            savedScore = scoreRepository.save(byScore.get());
        }
        return FilterScore(savedScore);
    }

    private ResponseScore FilterScore(Score scoreBy) {

        Optional<List<Score>> byScoreAll = scoreRepository.findByScoreAll(Date.valueOf(LocalDate.now()));

        byScoreAll.get().sort(
                Comparator.<Score>comparingInt(Score::getScore)
                        .thenComparing(score ->
                                Math.abs(ChronoUnit.SECONDS.between(score.getStart_date().toInstant(), score.getEnd_date().toInstant())))
                        .reversed()
        );

        for (Score score : byScoreAll.get()) {
            System.out.println(score.getScore() + " score " + score.getUser().getId());
        }

        int rating = 0;
        for (int i = 0; i < byScoreAll.get().size(); i++) {
            if (Objects.equals(byScoreAll.get().get(i).getId(), scoreBy.getId())) {
                System.out.println(byScoreAll.get().get(i).getScore());
                System.out.println(i + 1);
                rating = i + 1;
            }
        }
        return ResponseScore.builder()
                .scoreToday(scoreBy.getScore())
                .rating(rating)
                .members(byScoreAll.get().size())
                .build();
    }

    public PageDTO findAll(Pageable pageable, Long userId, ScoreDateDTO dto) {

        PageDTO pageDTO = PageDTO.builder()
                .user(authUserRepository.findById(userId).get())
                .lists(scoreRepository.pageAll(pageable,Date.valueOf(dto.getStartDate()),Date.valueOf(dto.getEndDate())))
                .build();

        return pageDTO;
    }
}
