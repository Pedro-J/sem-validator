package com.semvalidator.validation;

import com.semvalidator.model.Checklist;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @Author Created by Pedro-J on 6/18/17.
 */

@Component
public class ChecklistFormValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return Checklist.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "form.notEmpty.title");
    }
}
