package com.bkg.coursemanager.entity;

public class Class {
	int id;
	String grade;
	int serial;
	String classroom;
	String time;
	String studentFile;
	String name;
	
	Course course;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStudentFile() {
		return studentFile;
	}

	public void setStudentFile(String studentFile) {
		this.studentFile = studentFile;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getName() {
		String name=this.grade+"-"+this.getSerial();
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
