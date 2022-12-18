package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.database.*;
import com.TravelAgency.rest.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.List;

import static com.TravelAgency.comunicates.Communicates.NOT_FOUND_WITH_ID;


@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }

    public Question addQuestion(Question question) {
        question.setAnswered(false);
        return questionRepository.save(question);
    }

    public Question answerToQuestion(Question question) {
        return questionRepository.save(question);
    }
}
