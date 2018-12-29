package com.bkg.coursemanager.entity;

import java.util.List;

public class Course {
	int id;
	String name;
	String intro;
	boolean isShareTeam;
	boolean isShareSeminar;
	String startTeamTime;
	String endTeamTime;
	double presentationWeight;
	double questionWeight;
	double reportWeight;
	int minMemberNumber;
	int maxMemberNumber;
	
	User teacher;
	Course teamMainCourse;
	Course seminarMainCourse;
	List<Course> conflictCourses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public boolean isShareTeam() {
		return isShareTeam;
	}

	public void setShareTeam(boolean isShareTeam) {
		this.isShareTeam = isShareTeam;
	}

	public boolean isShareSeminar() {
		return isShareSeminar;
	}

	public void setShareSeminar(boolean isShareSeminar) {
		this.isShareSeminar = isShareSeminar;
	}

	public String getStartTeamTime() {
		return startTeamTime;
	}

	public void setStartTeamTime(String startTeamTime) {
		this.startTeamTime = startTeamTime;
	}

	public String getEndTeamTime() {
		return endTeamTime;
	}

	public void setEndTeamTime(String endTeamTime) {
		this.endTeamTime = endTeamTime;
	}

	public double getPresentationWeight() {
		return presentationWeight;
	}

	public void setPresentationWeight(double presentationWeight) {
		this.presentationWeight = presentationWeight;
	}

	public double getQuestionWeight() {
		return questionWeight;
	}

	public void setQuestionWeight(double questionWeight) {
		this.questionWeight = questionWeight;
	}

	public double getReportWeight() {
		return reportWeight;
	}

	public void setReportWeight(double reportWeight) {
		this.reportWeight = reportWeight;
	}

	public int getMinMemberNumber() {
		return minMemberNumber;
	}

	public void setMinMemberNumber(int minMemberNumber) {
		this.minMemberNumber = minMemberNumber;
	}

	public int getMaxMemberNumber() {
		return maxMemberNumber;
	}

	public void setMaxMemberNumber(int maxMemberNumber) {
		this.maxMemberNumber = maxMemberNumber;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public Course getTeamMainCourse() {
		return teamMainCourse;
	}

	public void setTeamMainCourse(Course teamMainCourse) {
		this.teamMainCourse = teamMainCourse;
	}

	public Course getSeminarMainCourse() {
		return seminarMainCourse;
	}

	public void setSeminarMainCourse(Course seminarMainCourse) {
		this.seminarMainCourse = seminarMainCourse;
	}
	
}
