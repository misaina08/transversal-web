/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Client;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author misa
 */
@Stateless
@LocalBean
public class ClientBean {

    @PersistenceContext(unitName = "Transversal-ejbPU")
    private EntityManager em;

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Client findByLogin(String login, String mdp){
        Query query = em.createQuery("SELECT c FROM Client c WHERE c.loin = :login and c.mdp = :mdp");
        query.setParameter("login", login);
        query.setParameter("mdp", mdp);
        return (Client)query.getSingleResult();
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
