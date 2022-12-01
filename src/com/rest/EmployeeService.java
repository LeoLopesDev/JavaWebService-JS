package com.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.entity.Employee;
import com.persistence.EmployeeDAO;

@Path("/employee")
public class EmployeeService {

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	private EmployeeDAO dao;

	public EmployeeService() {

		dao = new EmployeeDAO();

	}

	protected void setRequest(HttpServletRequest request) {

		this.request = request;

	}

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response EmployeeCreate(Employee employee) {

		try {
			employee = dao.save(employee);

			return Response.status(Status.CREATED).entity(employee).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();

		}

	}

	@Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
	public Response EmployeeRead() {
		List<Employee> employees = new ArrayList<>();

		try {

			employees = dao.findAll();

		} catch (Exception ex) {
			
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar funcionários =/").build();

		}

		GenericEntity<List<Employee>> entity = new GenericEntity<List<Employee>>(employees) {

		};

		return Response.status(Status.OK).entity(entity).build();

	}

	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response EmployeeUpdate(@PathParam("id") Integer id, Employee Employee) {

		try {

			if (dao.findById(id) == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			Employee.setId(id);
			Employee employee = dao.update(Employee);
			return Response.status(Status.OK).entity(employee).build();
		} catch (Exception ex) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar funcionário").build();
		}

	}

	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response EmployeeDelete(@PathParam("id") Integer id) {

		try {
			if (dao.findById(id) == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			dao.deleteById(id);
			return Response.status(Status.NO_CONTENT).build();
		} catch (Exception ex) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
	public Response EmployeeFindById(@PathParam("id") Integer id) {
		Employee employee = new Employee();

		try {

			employee = dao.findById(id);

		} catch (Exception ex) {
			
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar funcionários =/").build();

		}

		GenericEntity<Employee> entity = new GenericEntity <Employee>(employee) {

		};

		return Response.status(Status.OK).entity(entity).build();

	}

	@Path("/teste")
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String TesteJersey() {
		return "Funcionando.";
	}

}
