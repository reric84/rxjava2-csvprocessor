package com.csvprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LastNameProcessor {

	private static final Logger log = LoggerFactory.getLogger(LastNameProcessor.class);

	public static Person process(final Person person) throws Exception {
		String lastName = person.getLastName().toUpperCase();
		person.setLastName(lastName);
		log.debug("Persion {}", person);

		return person;
	}

}
