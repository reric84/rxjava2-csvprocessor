package com.csvprocessor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Niraj Sonawane Service class for Database related work
 *
 */
@Component
public class DaoServiceImpl {
	private static final Logger log = LoggerFactory.getLogger(DaoServiceImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	


	public void insertTeenagerList(List<Person> batchMessage) {

		log.info("Inserting data Batch Size {} ", batchMessage.size());
		try {

			jdbcTemplate.batchUpdate("INSERT INTO TEENAGER (first_name,last_name) VALUES (?, ?)", new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Person person = batchMessage.get(i);
					ps.setString(1, person.getFirstName());
					ps.setString(2, person.getLastName());
				}

				@Override
				public int getBatchSize() {
					return batchMessage.size();
				}
			});

		} catch (Exception e) {
			log.error("Error While Inserting ", e);

		}
	}
	public void insertYoungadultList(List<Person> batchMessage) {

		log.info("Inserting data Batch Size {} ", batchMessage.size());
		try {

			jdbcTemplate.batchUpdate("INSERT INTO YOUNGADULT (first_name,last_name) VALUES (?, ?)", new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Person person = batchMessage.get(i);
					ps.setString(1, person.getFirstName());
					ps.setString(2, person.getLastName());
				}

				@Override
				public int getBatchSize() {
					return batchMessage.size();
				}
			});

		} catch (Exception e) {
			log.error("Error While Inserting ", e);

		}
	}

	public void insertSeniorcitizensList(List<Person> batchMessage) {

		log.info("Inserting data Batch Size {} ", batchMessage.size());
		try {

			jdbcTemplate.batchUpdate("INSERT INTO SENIORCITIZENS (first_name,last_name) VALUES (?, ?)", new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Person person = batchMessage.get(i);
					ps.setString(1, person.getFirstName());
					ps.setString(2, person.getLastName());
				}

				@Override
				public int getBatchSize() {
					return batchMessage.size();
				}
			});

		} catch (Exception e) {
			log.error("Error While Inserting ", e);

		}
	}


	
	public Integer getTotalRowCountSeniorcitizens() {
		return jdbcTemplate.queryForObject("SELECT count(*) FROM SENIORCITIZENS", Integer.class);
	}
	public Integer getTotalRowCountYoungadult() {
		return jdbcTemplate.queryForObject("SELECT count(*) FROM YOUNGADULT", Integer.class);
	}
	public Integer getTotalRowCountTeenager() {
		return jdbcTemplate.queryForObject("SELECT count(*) FROM TEENAGER", Integer.class);
	}
}
