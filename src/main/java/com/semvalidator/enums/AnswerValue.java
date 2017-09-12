package com.semvalidator.enums;

/**
 * @Author Created by comp-dev on 6/12/17.
 */
public enum AnswerValue {
    PLEASED(0, "enum.answer.pleased"),
    NOT_PLEASED(1, "enum.answer.not_pleased"),
    PARTIALY_PLEASED(2, "enum.answer.partialy_pleased");

    private Integer code;
    private String messageCode;

    private AnswerValue(Integer code, String messageCode){
        this.code = code;
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public Integer getCode() {
        return code;
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
