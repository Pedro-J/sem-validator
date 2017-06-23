package com.semvalidator.enums;

/**
 * @Author Created by comp-dev on 6/12/17.
 */
public enum AnswerValue {
    APPLY(0, "enum.answer.apply"),
    NOT_APPLY(1, "enum.answer.not_apply"),
    NOT_KNOW(2, "enum.answer.not_know"),
    NOT_UNDERSTAND(3, "enum.answer.not_understand");

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
