/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Rate;
import static entity.Role_.users;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Orvur
 */
public class RateFacade {

    EntityManagerFactory emf;

    public RateFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Rate> getRates() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Rate> rates = (TypedQuery<Rate>) em.createNativeQuery("SELECT * FROM rate");
        return rates.getResultList();
    }
    
    public static void main(String[] args) {
        RateFacade rf = new RateFacade(Persistence.createEntityManagerFactory("pu_development"));
        List<Rate> rates = rf.getRates();
        System.out.println(rates.get(0).getDate());
    }
}
