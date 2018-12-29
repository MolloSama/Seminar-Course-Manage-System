package com.bkg.coursemanager.entity;

public class Attendance {
	int id;
	String pptName;
	String reportName;
	String pptUrl;
	String reportUrl;
	int teamOrder;
	boolean presented;
	
	Team team;
	ClassSeminar classSeminar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPptName() {
		return pptName;
	}
	public void setPptName(String pptName) {
		this.pptName = pptName;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getPptUrl() {
		return pptUrl;
	}
	public void setPptUrl(String pptUrl) {
		this.pptUrl = pptUrl;
	}
	public String getReportUrl() {
		return reportUrl;
	}
	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}
	public int getTeamOrder() {
		return teamOrder;
	}
	public void setTeamOrder(int teamOrder) {
		this.teamOrder = teamOrder;
	}
	public boolean isPresented() {
		return presented;
	}
	public void setPresented(boolean presented) {
		this.presented = presented;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public ClassSeminar getClassSeminar() {
		return classSeminar;
	}
	public void setClassSeminar(ClassSeminar classSeminar) {
		this.classSeminar = classSeminar;
	}
	
	
	
}