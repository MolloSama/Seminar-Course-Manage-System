package com.bkg.coursemanager.entity;

public class ClassSeminar {
	int id;
	String reportDDL;
	int status;
	
	Class class1;
	Seminar seminar;
	Boolean presented;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReportDDL() {
		return reportDDL;
	}
	public void setReportDDL(String reportDDL) {
		this.reportDDL = reportDDL;
	}
	public Class getClass1() {
		return class1;
	}
	public void setClass1(Class class1) {
		this.class1 = class1;
	}
	public Seminar getSeminar() {
		return seminar;
	}
	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	}
	public Boolean getPresented() {
		return presented;
	}
	public void setPresented(Boolean presented) {
		this.presented = presented;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
