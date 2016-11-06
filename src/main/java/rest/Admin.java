package rest;

import com.google.gson.Gson;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import security.PasswordStorage;
import entity.User;

@Path("admin")
@RolesAllowed("Admin")
public class Admin {

    private static final Gson gson = new Gson();
    private UserFacade facade = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));

    @Path("/users")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        List<User> users = facade.getAllUsers();
        String j = gson.toJson(users);
        return (j);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
        return "{\"message\" : \"REST call accesible by only authenticated ADMINS\",\n" + "\"serverTime\": \"" + now + "\"}";
    }

    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/user/{id}") 
    public String deleteUser(@PathParam("id") String userName) {
        System.out.println("heyehe");
        facade.deleteUserById(userName);
        return "worked";
    }
}
