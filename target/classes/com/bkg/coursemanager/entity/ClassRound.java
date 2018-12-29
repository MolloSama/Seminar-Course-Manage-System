package com.bkg.coursemanager.entity;

public class ClassRound {
	int id;
	int enrollNumber;
	
	Class class1;
	Round round;
	
	public int getEnrollNumber() {
		return enrollNumber;
	}
	public void setEnrollNumber(int enrollNumber) {
		this.enrollNumber = enrollNumber;
	}
	public Round getRound() {
		return round;
	}
	public void setRound(Round round) {
		this.round = round;
	}
	public Class getClass1() {
		return class1;
	}
	public void setClass1(Class class1) {
		this.class1 = class1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
