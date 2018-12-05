package com.csvprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AgeProcessor{

	private static final Logger log = LoggerFactory.getLogger(AgeProcessor.class);
	
	public static Person process(Person person) throws Exception {

		if (person.getAge() <=18) {
			person.setAgeGroup("TEENAGER");
		} else if (person.getAge() > 18 && person.getAge() <= 60) {
			person.setAgeGroup("YOUNGADULT");
		} else if (person.getAge() > 60) {
			person.setAgeGroup("SENIORCITIZENS");
		}
		log.info("Persion {}",person);
		return person;
	}

}
