package com.semvalidator.util;

/**
 * @Author Created by Pedro-J on 6/23/17.
 */
public class AnswerJsonDTO {
    private Integer value;
    private Integer question;
    private Integer model;
    private Integer requirement;

    public Integer getQuestion() {
        return question;
    }

    public void setQuestion(Integer question) {
        this.question = question;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getRequirement() {
        return requirement;
    }
    public void setRequirement(Integer requirement) {
        this.requirement = requirement;
    }
}
