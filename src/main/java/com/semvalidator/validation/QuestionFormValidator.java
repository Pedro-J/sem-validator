package com.semvalidator.validation;


import com.semvalidator.model.Question;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @Author Created by Pedro-J on 6/15/17.
 */
@Component
public class QuestionFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Question.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requirement", "form.requirement.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "criterion", "form.criterion.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "form.notEmpty.description");
    }
}
