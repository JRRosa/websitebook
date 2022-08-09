package org.websitebook.ws.domain.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.websitebook.ws.domain.dao.UserDAO;
import org.websitebook.ws.domain.dao.entities.User;
import org.websitebook.ws.domain.dao.exceptions.DBException;

@Dependent
@Path("/user")
public class UserResource {

	@Inject
	private UserDAO userDAO;

	private User user = null;
	private List<User> listUser = null;
	
	@GET
	public Response list(){
		listUser = userDAO.findAll();
		if(listUser != null){
			return Response.ok(listUser, MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
/* 	@GET 
	@Path("{id}")
	public Response getUserById(@PathParam("id") String userId) {
		user = userDAO.getById(Long.parseLong(userId)); 
		if(user != null){
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@POST 
	@Path("/deleteUser/{id}")
	public Response deleteUserById(@PathParam("id") String userId) {
		try {
			User user = userDAO.getById(Long.parseLong(userId));
			if(user != null) {
				userDAO.delete(user);
				return Response.ok().build();
			}
		} catch (DBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.notModified().build();
	}
	
	@POST 
	@Path("/create")
	public Response create(User userPage) {
		try {
			if (userPage != null) {
				user = userDAO.create(userPage);
				return Response.ok().build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.notModified().build();
	}
	
	@POST 
	public Response update(User userPage) {
		try {
			if (userPage != null) {
				//user = userDAO.update(userPage);
				return Response.ok().build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.notModified().build();
	} */
	
	

}
