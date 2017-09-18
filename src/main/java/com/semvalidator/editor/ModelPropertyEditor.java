package com.semvalidator.editor;

import com.semvalidator.model.ModelSE;
import com.semvalidator.service.ModelService;

import java.beans.PropertyEditorSupport;

public class ModelPropertyEditor extends PropertyEditorSupport {

    private final ModelService modelService;

    public ModelPropertyEditor(ModelService modelService){
        this.modelService = modelService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        Integer id = Integer.valueOf(text);

        if( id == null || id.intValue() == 0){
            setValue(null);
        }else {
            ModelSE entity = modelService.findById(id);
            setValue(entity);
        }
    }
}
