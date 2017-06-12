package com.semvalidator.validation;

import com.semvalidator.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProdutoValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");

		User produto = (User) target;
/*		if (produto.getPaginas() <= 0) {
			errors.rejectValue("paginas", "field.required");
		}*/
	}
	
}




