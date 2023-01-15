package com.pract.validation;

import java.util.ArrayList;
import java.util.List;

import com.pract.annotation.PasswordValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidatorClass implements ConstraintValidator<PasswordValidator, String>{

	List<String>weakPasswords=new ArrayList<>();
	
	@Override
	public void initialize(PasswordValidator p) {
		weakPasswords.add("12345");
		weakPasswords.add("abdaal");
		weakPasswords.add("ahmad");
		weakPasswords.add("amani");
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		System.out.println("value is="+value);
		return value!=null && (!(weakPasswords.contains(value)));
		
	}

}
