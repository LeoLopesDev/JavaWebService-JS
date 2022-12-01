package com.test;

import com.persistence.DepartmentDAO;

import com.entity.Department;

@Test
public class DepartmentTest {
	
	public Department saveDepartmentTest() {
		
	DepartmentDAO dao = new DepartmentDAO();
	
	Department department = new Department();
	
	department.setName("Desenvolvimento");
	department.setId(1);
	
	dao.save(department);
	
	return department;
	}
	

}

