package org.websitebook.ws.domain.service;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.websitebook.ws.domain.dao.entities.User;
import org.websitebook.ws.domain.dao.exceptions.APPException;
import org.websitebook.ws.domain.dao.exceptions.DBException;
import org.websitebook.ws.domain.dao.impl.UserDAOImpl;

@Path("/user")
public class UserResource {
	
	private UserDAOImpl userDAOImpl = UserDAOImpl.getInstance();
	private User user = null;
	private List<User> listUser = null;
	
	@GET
	public Response list(){
		try {
			listUser = userDAOImpl.getAll(); 
			return Response.ok(listUser, MediaType.APPLICATION_JSON).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET 
	@Path("{id}")
	public Response getUserById(@PathParam("id") String userId) {
		try {
			user = userDAOImpl.getById(Long.parseLong(userId)); 
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@POST 
	@Path("/deleteUser/{id}")
	public Response deleteUserById(@PathParam("id") String userId) {
		try {
			User user = userDAOImpl.getById(Long.parseLong(userId));
			if(user != null) {
				userDAOImpl.delete(user);
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
				user = userDAOImpl.create(userPage);
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
	public Response update(User userPage) {
		try {
			if (userPage != null) {
				user = userDAOImpl.update(userPage);
				return Response.ok().build();
			}
			
		} catch (DBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.notModified().build();
	}
	
	

}
