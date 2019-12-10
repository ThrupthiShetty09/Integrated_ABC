package com.slk.model;

import java.util.Date;

import org.springframework.stereotype.Repository;
@Repository
public class A_Employee {
	private int emp_no;
	private String name;
	private String dob;
	private int phno;
	private String username;
	private String password;
	private String emp_role;
	
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getPhno() {
		return phno;
	}
	public void setPhno(int phno) {
		this.phno = phno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmp_role() {
		return emp_role;
	}
	public void setEmp_role(String emp_role) {
		this.emp_role = emp_role;
	}
	@Override
	public String toString() {
		return "Employee [emp_no=" + emp_no + ", name=" + name + ", dob=" + dob + ", phno=" + phno + ", username="
				+ username + ", password=" + password + ", emp_role=" + emp_role + "]";
	}
	
}
