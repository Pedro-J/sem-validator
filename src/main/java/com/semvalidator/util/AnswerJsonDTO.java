package com.semvalidator.util;

/**
 * @Author Created by Pedro-J on 6/23/17.
 */
public class AnswerJsonDTO {
    private Integer value;
    private Integer question;
    private Integer checklist;
    private Integer requirement;

    public Integer getQuestion() {
        return question;
    }

    public void setQuestion(Integer question) {
        this.question = question;
    }

    public Integer getChecklist() {
        return checklist;
    }

    public void setChecklist(Integer checklist) {
        this.checklist = checklist;
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
