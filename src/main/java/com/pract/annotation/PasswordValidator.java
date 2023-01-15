package com.pract.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.pract.validation.PasswordValidatorClass;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = PasswordValidatorClass.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {

	String message() default "Please choose a strong password";
	 Class<?>[] groups() default{};
	 Class<? extends Payload>[] payload() default {};
}
