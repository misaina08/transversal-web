/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Categorie;
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
public class CategorieBean {

    @PersistenceContext(unitName = "Transversal-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    
    public Categorie findById(Integer id) {
        Query cl = em.createNamedQuery("Categorie.findById");
        cl.setParameter("id", id);
        return (Categorie) cl.getSingleResult();
    }
    
    public List<Categorie> findAll(){
        Query query = em.createQuery("SELECT c FROM Categorie c");
        return (List<Categorie>)query.getResultList();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
}
