/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import facades.UserFacade;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.hamcrest.Matchers.*;
import rest.Admin;
import security.PasswordStorage;

/**
 *
 * @author Oliver
 */
public class ServiceIntegrationTest {

    private static final Gson gson = new Gson();

    public ServiceIntegrationTest() {
    }

    @BeforeClass
    public static void setUpBeforeAll() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/seedMaven";
        RestAssured.defaultParser = Parser.JSON;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_development");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void serverIsRunning() {
        given().when().get().then().statusCode(200);
    }

    @Test
    public void getAllUsers() {
        UserFacade UF = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));
        Admin admin = new Admin();
        UF.createUser("User1", "Password1");
        UF.createUser("User2", "Password2");
        UF.createUser("User3", "Password3");
        given().when().get("/api/admin/users").then().statusCode(200).body(admin.getAllUsers(), equalTo(gson.toJson(UF.getAllUsers())));

    }
}
