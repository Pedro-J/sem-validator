package com.semvalidator.editor;

import com.semvalidator.model.Checklist;
import com.semvalidator.service.ChecklistService;

import java.beans.PropertyEditorSupport;

/**
 * @Author Created by Pedro-J on 6/19/17.
 */
public class ChecklistPropertyEditor extends PropertyEditorSupport {
    private ChecklistService checklistService;

    public ChecklistPropertyEditor(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        Integer id = Integer.valueOf(text);

        if( id == null || id.intValue() == 0){
            setValue(null);
        }else {
            Checklist entity = checklistService.findById(id);
            setValue(entity);
        }
    }
}
