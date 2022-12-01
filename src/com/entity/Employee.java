package com.entity;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String cpf;
	private String email;
	private int idDepartment;
	private Double baseSalary;
	private Date birthday;
	
	public Employee() {
		
	}

	public Employee(int id, String name, String cpf, String email, int idDepartment, Double baseSalary, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.idDepartment = idDepartment;
		this.baseSalary = baseSalary;
		this.birthday = birthday;
	}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(int idDepartment) {
		this.idDepartment = idDepartment;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}