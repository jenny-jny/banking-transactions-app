package com.revature.model;

public class Employee {

	private char username;
	private char password;
	
	public Employee() {
		super();
	}

	public Employee(char userName, char passWord) {
		super();
		this.username = userName;
		this.password = passWord;
	}

	public char getUserName() {
		return username;
	}

	public void setUserName(char userName) {
		this.username = userName;
	}

	public char getPassWord() {
		return password;
	}

	public void setPassWord(char passWord) {
		this.password = passWord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + password;
		result = prime * result + username;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (password != other.password)
			return false;
		if (username != other.username)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [userName=" + username + ", passWord=" + password + "]";
	}
	
}
