package com.example.mieszkaniaSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springdemo.domain.Person;

@Component
public class PersonManager {

	@Autowired
	private Person person;

	public String sayName() {
		return "I'm " + person.getFirstName() + "!";
	}

}
