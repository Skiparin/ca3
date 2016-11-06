/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.User;
import facades.UserFacade;
import java.util.List;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import security.IUser;
import security.PasswordStorage;

/**
 *
 * @author Oliver
 */
public class UserFacadeTest {
    UserFacade UF = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));
    public UserFacadeTest() {
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

    /**
     * Test of getUserByUserId method, of class UserFacade.
     */


    /**
     * Test of authenticateUser method, of class UserFacade.
     */
    

    /**
     * Test of createUser method, of class UserFacade.
     */
    @Test
    public void testCreateUser() throws PasswordStorage.CannotPerformOperationException {
        User user = UF.createUser("User1", "test1");
        assertEquals(user.getUserName(), "User1");
    }

    /**
     * Test of getAllUsers method, of class UserFacade.
     */
    @Test
    public void testGetAllUsers() {
        UF.createUser("User","test");
        List<User> UL = UF.getAllUsers();
        User user = UL.get(0);
        assertEquals(user.getUserName(), "User");
    }
    
    @Test
    public void testDeleteUserById() {
        UF.createUser("User", "test");
        assertEquals(UF.getUserByUserId("User"), "User");
    }
}
