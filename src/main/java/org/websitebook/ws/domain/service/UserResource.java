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

import org.websitebook.ws.domain.dao.UserDao;
import org.websitebook.ws.domain.dao.entities.User;

@Dependent
@Path("/user")
public class UserResource {

	@Inject
	private UserDao userDAO;
	
	@GET
	@Path("/create")
	public Response create(/* User userPage */) {
		
		User userPage = new User();
		userPage.setEmail("htv934@gmail.com");
		userPage.setPassword("test");
		userPage.setFirstName("Hamilton1");
		userPage.setLastName("Taveras1");
		userPage.setGender(1);
		userPage.setUserTypeId(1);
		
		try {
			if (userPage != null) {
				User user = userDAO.create(userPage);
				return Response.ok().build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.notModified().build();
	}

	@GET
	public Response list(){
		List<User> users = userDAO.findAll();
		if(users != null){
			return Response.ok(users, MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET 
	@Path("/{id}")
	public Response getUserById(@PathParam("id") String userId) {
		if(userId != null){
			User user = userDAO.findById(Long.parseLong(userId)); 
			if(user != null){
				return Response.ok(user, MediaType.APPLICATION_JSON).build();
			}
		}
		
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@GET
	@Path("/update")
	public Response update(/* User userPage */) {

		User userPage = new User();
		userPage.setId(Long.parseLong("17"));
		userPage.setEmail("user018@semanticsquare.com");
		userPage.setPassword("test");
		userPage.setFirstName("Jairo");
		userPage.setLastName("JR");
		userPage.setGender(0);
		userPage.setUserTypeId(2);

		try {
			if (userPage != null) {
				userDAO.update(userPage);
				return Response.ok().build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.notModified().build();
	}

	@GET
	@Path("/delete/{id}")
	public Response deleteUserById(@PathParam("id") String userId) {
		try {
			if(userId != null) {
				userDAO.delete(Long.parseLong(userId));
				return Response.ok().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.notModified().build();
	}

}
