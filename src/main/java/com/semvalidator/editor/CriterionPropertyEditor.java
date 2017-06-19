package com.semvalidator.editor;

import com.semvalidator.model.Criterion;
import com.semvalidator.service.CriterionService;

import java.beans.PropertyEditorSupport;

/**
 * @Author Created by Pedro-J on 6/18/17.
 */
public class CriterionPropertyEditor extends PropertyEditorSupport {

    private final CriterionService criterionService;

    public CriterionPropertyEditor(CriterionService criterionService){
        this.criterionService = criterionService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        Integer id = Integer.valueOf(text);

        if( id == null || id.intValue() == 0){
            setValue(null);
        }else {
            Criterion entity = criterionService.findById(id);
            setValue(entity);
        }
    }
}