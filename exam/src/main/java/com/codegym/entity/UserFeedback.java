package com.codegym.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String userFeedback;

    @OneToMany(mappedBy = "userFeedback", cascade = CascadeType.ALL)
    private List<Question> questionList;

    public UserFeedback() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(String userFeedback) {
        this.userFeedback = userFeedback;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
