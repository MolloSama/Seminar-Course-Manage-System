package com.bkg.coursemanager.entity;

public class RoundScore {
	double presentationScore;
	double reportScore;
	double questionScore;
	double totalScore;
	
	Team team;
	ClassRound classRound;
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
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	public ClassRound getClassRound() {
		return classRound;
	}
	public void setClassRound(ClassRound classRound) {
		this.classRound = classRound;
	}
	
	
}
