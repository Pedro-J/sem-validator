package com.semvalidator.editor;

import com.semvalidator.model.Requirement;
import com.semvalidator.service.RequirementService;

import java.beans.PropertyEditorSupport;

/**
 * @Author Created by Pedro-J on 6/19/17.
 */
public class RequirementPropertyEditor extends PropertyEditorSupport {

    private RequirementService requirementService;

    public RequirementPropertyEditor(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        Integer id = Integer.valueOf(text);

        if( id == null || id.intValue() == 0){
            setValue(null);
        }else {
            Requirement entity = requirementService.findById(id);
            setValue(entity);
        }
    }
}
