/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import facades.UserFacade;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import entity.User;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author plaul1
 */
@Path("/demoall")
public class All {

    private static final Gson gson = new Gson();
    private UserFacade facade = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of A
     */
    public All() {
    }

    /**
     * Retrieves representation of an instance of rest.All
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText() {
        System.out.println("XXXXXXXX---> " + System.getProperty("java.version"));
        return " {\"message\" : \"result for all\"}";
    }

    @Path("/user")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createUser(String user) {
        User u = (User) gson.fromJson(user, User.class);
        System.out.println(u.getUserName() + u.getPassword());
        facade.createUser(u.getUserName(), u.getPassword());
        return "worked";
    }

    @DELETE
    //@Consumes(MediaType.TEXT_PLAIN)
    //@Produces(MediaType.TEXT_PLAIN)
    @Path("/delete")
    public User deleteUser(String userName) {
        System.out.println("heyehe");
        User u = facade.deleteUserById(userName);
        return u;
    }
}
