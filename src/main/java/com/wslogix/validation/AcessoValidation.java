package com.wslogix.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=AcessoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface AcessoValidation {
	String message() default "erro_de_validacao";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
