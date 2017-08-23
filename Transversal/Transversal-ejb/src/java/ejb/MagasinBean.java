/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.CategorieMagasinView;
import entity.Magasin;
import entity.ProduitView;
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Magasin findUtilisateur(String login, String mdp) {
        try {
            Query query = em.createQuery("SELECT c FROM Magasin c WHERE c.login = :login and c.mdp = :mdp ");
            query.setParameter("login", login);
            query.setParameter("mdp", mdp);
            return ((Magasin) query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ProduitView> findMeilleursMagasin(Integer idmagasin) {
        Query query = em.createQuery("SELECT c FROM ProduitView c WHERE c.magasinId = :idmagasin order by c.dateajout desc, c.nbvues desc");
        query.setParameter("idmagasin", idmagasin);
        query.setMaxResults(8);
        return query.getResultList();
    }
    public List<CategorieMagasinView> findCategoriesMagasin(Integer idmagasin){
         Query query = em.createQuery("SELECT c FROM CategorieMagasinView c WHERE c.magasinId = :idmagasin");
        query.setParameter("idmagasin", idmagasin);
        return query.getResultList();
    }
    
    public List<ProduitView> findProduitsCategorieMagasin(Integer idmagasin, Integer idcategorie){
        Query query = em.createQuery("SELECT c FROM ProduitView c WHERE c.magasinId = :idmagasin and c.categorieId = :idcategorie");
        query.setParameter("idmagasin", idmagasin);
        query.setParameter("idcategorie", idcategorie);
        return query.getResultList();
    }
}
