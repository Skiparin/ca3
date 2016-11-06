/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Rate;
import facades.RateFacade;
import facades.UserFacade;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Orvur
 */
@Path("currency")
public class Currency {

    private static final Gson gson = new Gson();
    private UserFacade facade = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Currency
     */
    public Currency() {
    }

    /**
     * Retrieves representation of an instance of rest.Currency
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of Currency
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }

    @Path("/rate")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllRates() {
        RateFacade rf = new RateFacade(Persistence.createEntityManagerFactory("pu_development"));
        List<Rate> rates = rf.getRates();
        return gson.toJson(rates);
    }
}
