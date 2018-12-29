package com.bkg.coursemanager.entity;

public class Question {
	int id;
	int score;
	int studentId;
	boolean asked;
	
	Team team;
	Attendance attendance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public boolean isAsked() {
		return asked;
	}
	public void setAsked(boolean asked) {
		this.asked = asked;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Attendance getAttendance() {
		return attendance;
	}
	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}
	
}