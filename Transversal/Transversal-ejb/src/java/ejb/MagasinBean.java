/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.EvenementMagasin;
import entity.Magasin;
import java.util.List;
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

    public Magasin findById(Integer id) {
        Query cl = em.createNamedQuery("Magasin.findById");
        cl.setParameter("id", id);
        return (Magasin) cl.getSingleResult();
    }
    
    public List<Magasin> findAll() {
        Query cl = em.createNamedQuery("Magasin.findAll");
        return (List<Magasin>) cl.getResultList();
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
    
    public List<EvenementMagasin> getEvenement(Magasin magasin){
       try{
            Query query=em.createQuery("SELECT c FROM EvenementMagasin c WHERE c.magasinId.id = :id");
            query.setParameter("id", magasin.getId());
            return ((List<EvenementMagasin>) query.getResultList());       
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
