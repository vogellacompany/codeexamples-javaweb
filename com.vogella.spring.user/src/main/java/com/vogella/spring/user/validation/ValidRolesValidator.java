package com.vogella.spring.user.validation;

import java.util.Collection;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.common.collect.Lists;

public class ValidRolesValidator implements ConstraintValidator<ValidRoles, Collection<String>> {

	private List<String> validRoles = Lists.newArrayList("ROLE_USER", "ROLE_ADMIN");

	@Override
	public boolean isValid(Collection<String> collection, ConstraintValidatorContext context) {
		return collection.stream().allMatch(validRoles::contains);
	}

}
