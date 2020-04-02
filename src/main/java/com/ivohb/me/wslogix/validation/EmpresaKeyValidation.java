package com.ivohb.me.wslogix.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=EmpresaKeyValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface EmpresaKeyValidation {
	String message() default "erro_de_validacao";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
