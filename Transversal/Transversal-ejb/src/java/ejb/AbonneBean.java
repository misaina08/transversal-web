/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Client;
import entity.Favori;
import entity.Magasin;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kiashi
 */
@Stateless
@LocalBean
public class AbonneBean {

    @PersistenceContext(unitName = "Transversal-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<Favori> getAbonne(Integer a) {
       
        Query q = em.createQuery("SELECT p FROM Favori p WHERE p.magasinId.id = :id");
        q.setParameter("id", a);
        return (List<Favori>)q.getResultList();
    }
    
    
}
