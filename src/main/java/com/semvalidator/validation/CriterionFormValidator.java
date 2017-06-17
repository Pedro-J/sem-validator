package com.semvalidator.validation;


import com.semvalidator.model.Criterion;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @Author Created by Pedro-J on 6/15/17.
 */
@Component
public class CriterionFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Criterion.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "form.notEmpty.description");
    }
}
