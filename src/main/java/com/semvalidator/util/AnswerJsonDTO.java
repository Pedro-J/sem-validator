package com.semvalidator.util;

/**
 * @Author Created by Pedro-J on 6/23/17.
 */
public class AnswerJsonDTO {
    private Integer value;
    private Integer question;
    private Integer evalution;

    public Integer getQuestion() {
        return question;
    }

    public void setQuestion(Integer question) {
        this.question = question;
    }

    public Integer getEvalution() {
        return evalution;
    }

    public void setEvalution(Integer evalution) {
        this.evalution = evalution;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


}
