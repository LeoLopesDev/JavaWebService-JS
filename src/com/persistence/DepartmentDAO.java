package com.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Department;

public class DepartmentDAO {

	public void save(Department department) {

		String sql = "INSERT INTO DEPARTMENT(name) VALUES (?)";

		Connection conn = null;
		PreparedStatement statement = null;

		try {

			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);

			statement.setString(1, department.getName());

			statement.execute();

		} catch (Exception ex) {

			throw new RuntimeException("Erro ao salvar setor" + ex.getMessage(), ex);

		} finally {

			ConnectionFactory.closeConnection(conn, statement);
		}

	}

	public Department update(Department department) {

		String sql = "UPDATE department SET name = ? "
				+ "WHERE id = ? ";

		Connection conn = null;
		PreparedStatement statement = null;
		Department updatedDepartment = null;

		try {

			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);

			statement.setString(1, department.getName());
			statement.setInt(2, department.getId());

			statement.execute();

		} catch (Exception ex) {
			throw new RuntimeException("Erro ao atualizar Departamento");
		} finally {

			ConnectionFactory.closeConnection(conn, statement);
		}

		return updatedDepartment;

	}

	public List<Department> findAll() throws SQLException {

		String sql = "SELECT * FROM department";
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		List<Department> departments = new ArrayList<Department>();

		try {
			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Department department = new Department();

				department.setId(resultSet.getInt("id"));
				department.setName(resultSet.getString("name"));

				departments.add(department);

			}

		} catch (Exception ex) {

			throw new RuntimeException("Erro ao listar Departamentos");

		} finally {

			ConnectionFactory.closeConnection(conn, statement, resultSet);

		}

		return departments;

	}

	public Department findById(int id) {

		String sql = "SELECT * FROM department WHERE id = ?";
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Department department = new Department();

		try {

			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);

			statement.setInt(1, id);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {


				department.setId(resultSet.getInt("id"));
				department.setName(resultSet.getString("name"));

			}

		} catch (SQLException ex) {
			
			throw new RuntimeException("Erro ao buscar setor =/", ex.fillInStackTrace());
			
		}finally {
			ConnectionFactory.closeConnection(conn, statement, resultSet);
		}

		return department;

	}

	public void deleteById(int id) {

		String sql = "DELETE FROM department WHERE id=?";
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);

			statement.setInt(1, id);

			statement.execute();

		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			ConnectionFactory.closeConnection(conn, statement);

		}
	}

}
