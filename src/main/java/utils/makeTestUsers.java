package utils;

import entity.Role;
import entity.User;
import facades.UserFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import security.IUser;

public class makeTestUsers {

    //Only for initial testing REMOVE BEFORE PRODUCTION
    //Run this file to setup the users required to use the initial version of the seed
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("pu_development").createEntityManager();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_development");
        try {
            UserFacade UF = new UserFacade(emf);
            System.out.println("Creating TEST Users");
            if (em.find(User.class, "user") == null) {
                em.getTransaction().begin();
                Role userRole = new Role("User");
                Role adminRole = new Role("Admin");
                User user = new User("Ørvur", "test");
                user.addRole(userRole);
                User admin = new User("Oliver", "test");
                admin.addRole(adminRole);
                User both = new User("Andreas", "test");
                both.addRole(userRole);
                both.addRole(adminRole);
                em.persist(userRole);
                em.persist(adminRole);
                em.persist(user);
                em.persist(admin);
                em.persist(both);
                em.getTransaction().commit();
                System.out.println("Created TEST Users");

                //IUser usertest =  UF.getUserByUserId("Ørvur");
                //System.out.println(user.getRoles().get(0).getRoleName());
                System.out.println(UF.getAllUsers().get(0).getUserName());
                System.out.println(UF.getAllUsers().get(0).getRoles().get(0).getRoleName());
            }
        } catch (Exception ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
