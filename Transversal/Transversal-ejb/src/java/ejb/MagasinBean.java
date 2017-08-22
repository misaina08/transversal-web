/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Magasin;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kiashi
 */
@Stateless
@LocalBean
public class MagasinBean {

    @PersistenceContext(unitName = "Transversal-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Magasin findUtilisateur(String login, String mdp){
        try{
            Query query=em.createQuery("SELECT c FROM Magasin c WHERE c.login = :login and c.mdp = :mdp ");
            query.setParameter("login", login);
            query.setParameter("mdp", mdp);
            return ((Magasin) query.getSingleResult());       
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
