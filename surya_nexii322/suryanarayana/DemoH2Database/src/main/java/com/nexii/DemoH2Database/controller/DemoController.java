package com.nexii.DemoH2Database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/createTable", method = RequestMethod.GET)
	public void createTable() {
		try {
			String query = "create table student(id serial,name varchar(44),age varchar(54))";
			jdbcTemplate.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/putData", method = RequestMethod.GET)
	public void putData() {
		String query = "insert into student(id,name,age) values(1,'surya','25')";
		jdbcTemplate.execute(query);
	}
}
