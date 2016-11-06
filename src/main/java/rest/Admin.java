package rest;

import com.google.gson.Gson;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import entity.User;
import security.PasswordStorage;
@Path("admin")
//@RolesAllowed("Admin")
public class Admin {

    private static final Gson gson = new Gson();
    private UserFacade facade = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));

    @Path("/users")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllUsers() {
        List<User> users = facade.getAllUsers();
        String j = gson.toJson(users);
        return (j);
    }

}
