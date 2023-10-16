package com.example.els_api.repository;

import com.example.els_api.domains.Score;
import com.example.els_api.dto.PageScoreDTO;
import com.example.els_api.dto.ResponseScore;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {


    @Query(value = "select k.id as userId, " +
            "       k.email, " +
            "       k.full_name as fullName, " +
            "       k.phone, " +
            "       k.photo, " +
            "       coalesce(total_score,0) as totalScore " +
            "from (select sum(s.score) as total_score, " +
            "        a.* " +
            "from score s " +
            "     left join auth_user a on a.state = 1 and a.id = s.user_id " +
            "where s.state = 1 " +
            "  and Date(s.start_date) >= Date(:start_date) " +
            "  and Date(s.end_date)  <= Date(:end_date) " +
            "group by a.id)k order by k.total_score desc ", nativeQuery = true)
    Page<PageScoreDTO> pageAll(Pageable pageable, @Param("start_date") Date startDate, @Param("end_date") Date endDate);

    @Query("SELECT s FROM Score s WHERE s.state = 1 AND s.user.id = :userId AND DATE(s.start_date) = :date")
    Optional<Score> findByScore(Long userId, Date date);

    @Query("select s from  Score s where s.state = 1 " +
            "and Date(s.start_date) = :date ")
    Optional<List<Score>> findByScoreAll(Date date);

}

