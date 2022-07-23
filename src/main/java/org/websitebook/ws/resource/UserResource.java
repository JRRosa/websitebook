package org.websitebook.ws.resource;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.websitebook.ws.entities.User;
import org.websitebook.ws.impl.UserDAOImpl;

@Path("/user")
public class UserResource {
	
	private UserDAOImpl userDao = new UserDAOImpl();
	private User user = null;
	
	@GET
	@Path("{id}")
	public Response getById(@PathParam("id") int id){
		try {
			user = userDao.getById(id);
			if(user != null)
				return Response.ok(user, MediaType.APPLICATION_JSON).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	

}
