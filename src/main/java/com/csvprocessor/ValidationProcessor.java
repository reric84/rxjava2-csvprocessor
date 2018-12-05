package com.csvprocessor;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationProcessor {

	private static final Logger log = LoggerFactory.getLogger(ValidationProcessor.class);
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public static Boolean process(final Person person) throws Exception {
		
		Set<ConstraintViolation<Person>> validate = validator.validate(person);
		return validate.isEmpty();
	}

}
