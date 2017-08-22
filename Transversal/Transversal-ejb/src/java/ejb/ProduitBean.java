/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.ProduitView;
import java.util.List;
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
public class ProduitBean {

    @PersistenceContext(unitName = "Transversal-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    /**
     * Retourne les produits récents, triés par ordre de vus (pour mobile)
     * @return 
     */
    public List<ProduitView> getRecent(){
        Query q = em.createQuery("select p from ProduitView p order by p.dateajout desc, p.nbvues desc");
        return q.getResultList();
    }
}
