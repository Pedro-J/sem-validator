package com.semvalidator.editor;

import com.semvalidator.model.Question;
import com.semvalidator.service.QuestionService;

import java.beans.PropertyEditorSupport;


/**
 * @Author Created by Pedro-J on 6/19/17.
 */
public class QuestionPropertyEditor extends PropertyEditorSupport {

    private QuestionService questionService;

    public QuestionPropertyEditor(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        Integer id = Integer.valueOf(text);

        if( id == null || id.intValue() == 0){
            setValue(null);
        }else {
            Question entity = questionService.findById(id);
            setValue(entity);
        }
    }
}
