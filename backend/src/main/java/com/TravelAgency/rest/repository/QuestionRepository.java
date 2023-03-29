package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findById(@Param("id") Long id);

    List<Question> findAll();
}
