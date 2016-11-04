/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.User;
import facades.UserFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import entity.Currency;
import entity.Rate;

/**
 *
 * @author Orvur
 */
public class makesTestCurrenies {

    public static void main(String[] args) {

        EntityManager em = Persistence.createEntityManagerFactory("pu_development").createEntityManager();

        try {
            System.out.println("Creating TEST Currencies");
            if (em.find(Currency.class, "DKK") == null) {
                em.getTransaction().begin();
                Currency currency = new Currency("Dkk", "yolo");
                Rate rate = new Rate("10-10-2016", "100");
                currency.addRate(rate);
                em.persist(currency);
                em.persist(rate);
                em.getTransaction().commit();
                UserFacade uf = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));
                uf.createUser("tests", "test");
                System.out.println("Created TEST Users");
            }
        } catch (Exception ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
