package com.csvprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FirstNameProcessor {

    private static final Logger log = LoggerFactory.getLogger(FirstNameProcessor.class);

    
    public static Person process(Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        person.setFirstName(firstName);
        log.debug("Persion {}",person);
        return person;
    }

}
