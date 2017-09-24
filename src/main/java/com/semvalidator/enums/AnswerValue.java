package com.semvalidator.enums;

/**
 * @Author Created by comp-dev on 6/12/17.
 */
public enum AnswerValue {
    PLEASED(0, "enum.answer.pleased", 1f),
    NOT_PLEASED(1, "enum.answer.not_pleased", 0.0f),
    PARTIALY_PLEASED(2, "enum.answer.partialy_pleased", 0.5f);

    private Integer code;
    private float satisfaction;
    private String messageCode;

    private AnswerValue(Integer code, String messageCode, float satisfaction){
        this.code = code;
        this.messageCode = messageCode;
        this.satisfaction = satisfaction;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public Integer getCode() {
        return code;
    }

    public float getSatisfaction() {
        return satisfaction;
    }

    public static AnswerValue findByCode(Integer code){
        for(AnswerValue value : values()){
            if( value.code.equals(code)){
                return value;
            }
        }
        return null;
    }
}
