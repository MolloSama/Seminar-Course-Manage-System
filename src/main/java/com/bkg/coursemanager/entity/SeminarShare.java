package com.bkg.coursemanager.entity;

public class SeminarShare {
	int id;
	
	Course mainCourse;
	Course subCourse;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Course getMainCourse() {
		return mainCourse;
	}
	public void setMainCourse(Course mainCourse) {
		this.mainCourse = mainCourse;
	}
	public Course getSubCourse() {
		return subCourse;
	}
	public void setSubCourse(Course subCourse) {
		this.subCourse = subCourse;
	}
	
	
}
