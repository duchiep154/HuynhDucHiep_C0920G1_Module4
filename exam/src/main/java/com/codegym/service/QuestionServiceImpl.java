package com.codegym.service;

import com.codegym.entity.*;
import com.codegym.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFeedbackRepository userFeedbackRepository;

    @Autowired
    private StatusRepository statusRepository;






    @Override
    public List<Question> findAll() {
        return this.questionRepository.findAll();
    }

    @Override
    public Question findById(Integer id) {
        return this.questionRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Question question) {
        this.questionRepository.save(question);

    }

    @Override
    public void update(Question question) {
        this.questionRepository.save(question);

    }

    @Override
    public void remove(Integer id) {
        this.questionRepository.deleteById(id);

    }

    @Override
    public List<QuestionType> findAllQuestionType() {
        return this.questionTypeRepository.findAll();
    }

    @Override
    public List<User> findAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public List<UserFeedback> findAllUserFeedback() {
        return this.userFeedbackRepository.findAll();
    }

    @Override
    public List<Status> findAllStatus() {
        return this.statusRepository.findAll();
    }
}
