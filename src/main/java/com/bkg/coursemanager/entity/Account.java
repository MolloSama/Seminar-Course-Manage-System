package com.bkg.coursemanager.entity;

public class Account {

	private String accountID;
	private String username;
	private String password;
	private String email;
	private String code;
	private String name;
	private String role;
	private int status;
	private int timeInterval;

	public Account() {
	}

	public Account(String accountID, String username, String password, String email, String code, String name, String role, int status, int timeInterval) {
		this.accountID = accountID;
		this.username = username;
		this.password = password;
		this.email = email;
		this.code = code;
		this.name = name;
		this.role = role;
		this.status = status;
		this.timeInterval = timeInterval;
	}

	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
		this.username=accountID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(int timeInterval) {
		this.timeInterval = timeInterval;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Account{" +
				"accountID='" + accountID + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", code='" + code + '\'' +
				", name='" + name + '\'' +
				", role='" + role + '\'' +
				", status=" + status +
				", timeInterval=" + timeInterval +
				'}';
	}
}

