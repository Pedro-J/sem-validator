package com.semvalidator.validation;

import com.semvalidator.model.Evaluation;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EvaluationFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Evaluation.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "form.notEmpty.model");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "checklist", "form.notEmpty.checklist");
    }
}