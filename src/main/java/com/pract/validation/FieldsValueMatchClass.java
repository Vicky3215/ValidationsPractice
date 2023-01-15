package com.pract.validation;

import org.springframework.beans.BeanWrapperImpl;

import com.pract.annotation.FieldsValueMatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldsValueMatchClass implements ConstraintValidator<FieldsValueMatch, Object>{

	
	private String fielding;
	private String fieldMatch;
	
	@Override
	public void initialize(FieldsValueMatch match) {
		this.fielding=match.field();
		this.fieldMatch=match.fieldMatch();
	}
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Object field=new BeanWrapperImpl(value).getPropertyValue(fielding);
		Object fieldMa=new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
		System.out.println("field"+field);
		System.out.println("fieldMa"+fieldMa);
		if(field!=null) {
			return field.equals(fieldMa);
		}else {
			fieldMa=null;	
		}
		return false;
	}

}
