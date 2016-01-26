package com.example.springdemo.domain;

public class Person {
	
	private String firstName = "Jan";
	private int yob = 1945;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getYob() {
		return yob;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}
}
