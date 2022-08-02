package org.websitebook.ws;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.websitebook.ws.entities.User;
import org.websitebook.ws.impl.UserDAOImpl;

@Path("/user")
public class UserResource {
	
	private UserDAOImpl userDAOImpl = UserDAOImpl.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> list(){
		try {
			return userDAOImpl.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET @Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") String userId) {
		try {
			return userDAOImpl.getById(Long.parseLong(userId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@POST @Path("deleteUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean deleteUserById(@PathParam("id") String userId) {
		try {
			User user = userDAOImpl.getById(Long.parseLong(userId));
			if(user != null) {
				return userDAOImpl.delete(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
