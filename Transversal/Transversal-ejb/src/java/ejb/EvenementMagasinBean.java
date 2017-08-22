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
public class EvenementMagasinBean {

    @PersistenceContext(unitName = "Transversal-ejbPU")
    private EntityManager em;

    public void ajoutEvenement(EvenementMagasin evenement) {
        FacesContext context = FacesContext.getCurrentInstance();
        Magasin a = (Magasin) context.getExternalContext().getSessionMap().get("magasinSession");
        evenement.setMagasinId(a);
        em.persist(evenement);
    }
    
    public List<EvenementMagasin> getListEvenement() {
        FacesContext context = FacesContext.getCurrentInstance();
        Magasin a = (Magasin) context.getExternalContext().getSessionMap().get("magasinSession");
        try {

            Query cl = em.createQuery("SELECT c FROM EvenementMagasin c WHERE c.magasinId.id = :id ");
            cl.setParameter("id", a.getId());

            return (List<EvenementMagasin>) cl.getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
