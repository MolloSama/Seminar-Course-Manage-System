package com.bkg.coursemanager.entity;


public class Seminar {

    int id;
	String topic;
	boolean visible;
	boolean isShare;
	String order;
	String intro;
	int teamNumLimit;
	String reportDDL;
	String signupStartTime;
	String signupEndTime;
	
	Course course;
	Class class1;
	Round round;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isShare() {
		return isShare;
	}

	public void setShare(boolean share) {
		isShare = share;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getTeamNumLimit() {
		return teamNumLimit;
	}

	public void setTeamNumLimit(int teamNumLimit) {
		this.teamNumLimit = teamNumLimit;
	}

	public String getReportDDL() {
		return reportDDL;
	}

	public void setReportDDL(String reportDDL) {
		this.reportDDL = reportDDL;
	}

	public String getSignupStartTime() {
		return signupStartTime;
	}

	public void setSignupStartTime(String signupStartTime) {
		this.signupStartTime = signupStartTime;
	}

	public String getSignupEndTime() {
		return signupEndTime;
	}

	public void setSignupEndTime(String signupEndTime) {
		this.signupEndTime = signupEndTime;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Class getClass1() {
		return class1;
	}

	public void setClass1(Class class1) {
		this.class1 = class1;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}
}
