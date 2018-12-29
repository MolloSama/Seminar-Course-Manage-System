package com.bkg.coursemanager.entity;

public class SeminarShareRequest {
	int id;
	Integer status;
	
	Course mainCourse;
	Course subCourse;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
