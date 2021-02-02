package com.codegym.service;

import com.codegym.entity.*;

import java.util.List;

public interface QuestionService {

    List<Question> findAll();

    Question findById(Integer id);

    void save(Question question);

    void update(Question question);

    void remove(Integer id);

    List<QuestionType> findAllQuestionType();

    List<User> findAllUser();

    List<UserFeedback> findAllUserFeedback();

    List<Status> findAllStatus();

}
