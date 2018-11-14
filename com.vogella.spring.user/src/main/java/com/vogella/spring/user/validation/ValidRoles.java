package com.vogella.spring.user.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidRolesValidator.class })
@Documented
public @interface ValidRoles {

	String message() default "Invalid role detected";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
