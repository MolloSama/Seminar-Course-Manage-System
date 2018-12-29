package com.bkg.coursemanager.entity;

public class SeminarScore {
	double presentationScore;
	double reportScore;
	double questionScore;
	double totalScore;
	
	Team team;
	ClassSeminar classSeminar;
	public double getPresentationScore() {
		return presentationScore;
	}
	public void setPresentationScore(double presentationScore) {
		this.presentationScore = presentationScore;
	}
	public double getReportScore() {
		return reportScore;
	}
	public void setReportScore(double reportScore) {
		this.reportScore = reportScore;
	}
	public double getQuestionScore() {
		return questionScore;
	}
	public void setQuestionScore(double questionScore) {
		this.questionScore = questionScore;
	}
	
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
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
