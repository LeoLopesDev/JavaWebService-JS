
package com.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Employee;

public class EmployeeDAO {

	public Employee save(Employee employee) throws Exception {

		String sql = "INSERT INTO EMPLOYEE (name, cpf, email, idDepartment, " + " baseSalary, birthday) "
				+ " VALUES(?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement statement = null;

		try {

			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, employee.getName());
			statement.setString(2, employee.getCpf());
			statement.setString(3, employee.getEmail());
			statement.setInt(4, employee.getIdDepartment());
			statement.setDouble(5, employee.getBaseSalary());
			statement.setDate(6, new Date(employee.getBirthday().getTime()));
			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();
			int id = 0;
			if (rs.next()) {

				id = rs.getInt(1);

				System.out.println("Id gerado pela inserção de" + employee.getName() + " é : " + id);
			}

			return employee;

		} catch (Exception ex) {
			throw ex;
		}

	}

	public Employee update(Employee employee) throws Exception {

		String sql = "UPDATE employee SET name=?, cpf=?, email=?, idDepartment=?, "
				+ "basesalary=?, birthday=? WHERE id = ?";

		Connection conn = null;
		PreparedStatement statement = null;

		try {

			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, employee.getName());
			statement.setString(2, employee.getCpf());
			statement.setString(3, employee.getEmail());
			statement.setInt(4, employee.getIdDepartment());
			statement.setDouble(5, employee.getBaseSalary());
			statement.setDate(6, new Date(employee.getBirthday().getTime()));
			statement.setInt(7, employee.getId());

			statement.executeUpdate();

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			ConnectionFactory.closeConnection(conn, statement);
		}
		
		System.out.println("Funcionário Atualizado!");
		return employee;

	}

	public void deleteById(int id) throws Exception {

		String sql = "DELETE FROM employee WHERE id = ?";

		Connection conn = null;
		PreparedStatement statement = null;
		

		try {

			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);

			statement.setInt(1, id);
			
			statement.execute();

		} catch (Exception ex) {

			throw ex;

		}

	}

	public List<Employee> findAll() throws Exception {

		String sql = "SELECT * FROM employee";

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		List<Employee> employees = new ArrayList<Employee>();

		try {

			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Employee employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setIdDepartment(resultSet.getInt("idDepartment"));
				employee.setName(resultSet.getString("name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setBaseSalary(resultSet.getDouble("basesalary"));
				employee.setCpf(resultSet.getString("cpf"));
				employee.setBirthday(resultSet.getDate("birthday"));

				employees.add(employee);

			}

		} catch (Exception ex) {

			throw ex;

		} finally {

			ConnectionFactory.closeConnection(conn, statement, resultSet);

		}

		return employees;

	}

	public Employee findById(int id) throws Exception {

		String sql = "SELECT * FROM employee WHERE id = ?";

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Employee employee = new Employee();

		try {

			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement.setInt(1, id);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				employee.setId(resultSet.getInt("id"));
				employee.setIdDepartment(resultSet.getInt("idDepartment"));
				employee.setName(resultSet.getString("name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setBaseSalary(resultSet.getDouble("basesalary"));
				employee.setCpf(resultSet.getString("cpf"));
				employee.setBirthday(resultSet.getDate("birthday"));

			}
			
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception ex) {

			ex.printStackTrace();

		}
		return employee;

	}
}
