package com.entity;

import java.util.ArrayList;
import java.util.List;

public class Department {

	private int id;
	private String name;
	private List<Employee> employees = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;

	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;

	}

	public Department(String name, int id, List<Employee> employees) {
		super();
		this.name = name;
		this.id = id;
		this.employees = employees;
	}

	public Department() {

	}

	@Override
	public String toString() {
		return "Department [name=" + name + ", id=" + id + ", employees=" + employees + "]";
	}

}
