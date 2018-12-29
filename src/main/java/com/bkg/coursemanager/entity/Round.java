package com.bkg.coursemanager.entity;

public class Round {
	int id;
	int order;
	String orderName;
	
	int calculatePreType;
	int calculateQueType;
	int calculateRepType;
	Course course;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getCalculatePreType() {
		return calculatePreType;
	}
	public void setCalculatePreType(int calculatePreType) {
		this.calculatePreType = calculatePreType;
	}
	public int getCalculateQueType() {
		return calculateQueType;
	}
	public void setCalculateQueType(int calculateQueType) {
		this.calculateQueType = calculateQueType;
	}
	public int getCalculateRepType() {
		return calculateRepType;
	}
	public void setCalculateRepType(int calculateRepType) {
		this.calculateRepType = calculateRepType;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getOrderName() {
		String orderCh = null;
		if(this.order==1)orderCh="第一轮";
		if(this.order==2)orderCh="第二轮";
		if(this.order==3)orderCh="第三轮";
		if(this.order==4)orderCh="第四轮";
		if(this.order==5)orderCh="第五轮";
		if(this.order==6)orderCh="第六轮";
		if(this.order==7)orderCh="第七轮";
		if(this.order==8)orderCh="第八轮";
		if(this.order==9)orderCh="第九轮";
		return orderCh;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	
	
}
