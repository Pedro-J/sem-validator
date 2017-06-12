package com.semvalidator.enums;

/**
 * @Author Created by comp-dev on 6/12/17.
 */
public enum AnswerValue {
    APPLY("enum.answer.apply"),
    NOT_APPLY("enum.answer.not_apply"),
    NOT_KNOW("enum.answer.not_know"),
    NOT_UNDERSTAND("enum.answer.not_understand");

    private String messageCode;

    private AnswerValue(String messageCode){
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }
}
