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

                em.getTransaction().begin();
                Currency currency = new Currency();
                currency.setCode("DKK");
                currency.setDesc("DANMARK");
                Rate rate = new Rate();
                rate.setDate("10-10-2016");
                rate.setRate("1.101");
                currency.addRate(rate);
                rate.setCurrency(currency);
                em.persist(currency);
                em.persist(rate);
                em.getTransaction().commit();
    }
}
