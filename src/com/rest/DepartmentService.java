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

import com.entity.Department;
import com.persistence.DepartmentDAO;

@Path("/department")
public class DepartmentService {

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	private DepartmentDAO dao;

	public DepartmentService() {
		dao = new DepartmentDAO();
	}

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response CreateDepartment(Department department) {

		try {
			

			dao.save(department);

			return Response.status(Status.CREATED).build();
		} catch (Exception ex) {

			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();

		}

	}

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response DepartmentRead() {
		List<Department> departments = new ArrayList<>();

		try {
			departments = dao.findAll();
		} catch (Exception ex) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar setores =( ").build();
		}

		GenericEntity<List<Department>> entity = new GenericEntity<List<Department>>(departments) {
		};
		return Response.status(Status.OK).entity(entity).build();

	}

	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response DepartmentUpdate(Department department, @PathParam("id") Integer id) {
		
		try { 
			
				if(dao.findById(id)==null) {
					
					return Response.status(Status.NOT_FOUND).build();
				}
				
				Department departmentr = dao.update(department);
				return Response.status(Status.OK).entity(departmentr).build();
			
		}catch (Exception ex) {
			
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao Atualizar Setores =(").build();
		}
		
	}
	
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response DepartmentDelete(@PathParam("id") Integer id) {
		
		try {
			if(dao.findById(id)==null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			dao.deleteById(id);
			return Response.status(Status.NO_CONTENT).build();
			
			
		}catch (Exception ex) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao Deletar Setor!").build();
		}
		
	}
		
	@Path("/teste")
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String TesteJersey() {
			return "Funcionando!";
		}
		
	}


