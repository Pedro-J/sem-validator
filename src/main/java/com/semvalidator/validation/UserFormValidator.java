package com.semvalidator.validation;

import com.semvalidator.model.User;
import com.semvalidator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "form.notEmpty.user.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.notEmpty.user.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "form.notEmpty.user.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "form.user.notEmpty.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","form.user.notEmpty.confirmPassword");

		if(!emailValidator.valid(user.getLogin())){
			errors.rejectValue("email", "form.pattern.user.email");
		}

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "form.diff.user.confirmPassword");
		}

	}

}