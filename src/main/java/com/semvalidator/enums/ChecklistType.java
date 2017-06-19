package com.semvalidator.enums;

/**
 * @Author Created by comp-dev on 6/14/17.
 */
public enum ChecklistType {
    VERIFICATION("enum.checklist.tipo.verification"),
    VALIDACAO("enum.checklist.tipo.validation");

    private String messageCode;

    private ChecklistType(String messageCode){
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }
}
