/**
 * 
 */
package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.service.UserService;

/**
 * @author mjf
 *
 */

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		userService.greeting("tom");
		System.out.println(value);
		return false;
	}

	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("initialize MyConstraint");
	}




}
