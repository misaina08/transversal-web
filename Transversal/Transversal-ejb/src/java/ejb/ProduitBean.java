/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Magasin;
import entity.Produit;
import entity.ProduitView;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.context.FacesContext;
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

    public void ajoutProduit(Produit produit) {
        FacesContext context = FacesContext.getCurrentInstance();
        Magasin a = (Magasin) context.getExternalContext().getSessionMap().get("magasinSession");
        produit.setMagasinId(a);
        produit.setDateajout(new Date());
        em.persist(produit);
    }

    
     public Produit update(Produit point) {
        return em.merge(point);
    }
    
    public Produit findById(Integer id) {
        return em.find(Produit.class, id);
    }

    public List<Produit> getListProduit() {
        FacesContext context = FacesContext.getCurrentInstance();
        Magasin a = (Magasin) context.getExternalContext().getSessionMap().get("magasinSession");
        try {

            Query cl = em.createQuery("SELECT c FROM Produit c WHERE c.magasinId.id = :id ");
            cl.setParameter("id", a.getId());

            return (List<Produit>) cl.getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

     public List<Produit> getList(Integer a) {
       
        try {

            Query cl = em.createQuery("SELECT c FROM Produit c WHERE c.magasinId.id = :id ");
            cl.setParameter("id", a);

            return (List<Produit>) cl.getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    
    /**
     * Retourne les produits récents, triés par ordre de vus (pour mobile)
     *
     * @return
     */
    public List<ProduitView> getRecent() {
        Query q = em.createQuery("select p from ProduitView p order by p.dateajout desc, p.nbvues desc");
        return q.getResultList();
    }
    
    public List<Produit> getDerniersAjout() {
        Query query = em.createQuery("SELECT p FROM Produit p ORDER BY p.dateajout DESC");
        query.setMaxResults(6);
        return (List<Produit>) query.getResultList();
    }
    
    public List<Produit> getSimilaire(Produit produit) {
        Query query = em.createQuery("SELECT p FROM Produit p WHERE p.categorieId = :cat");
        query.setParameter("cat", produit.getCategorieId());
        query.setMaxResults(6);
        return (List<Produit>) query.getResultList();
    }
    
    public List<Produit> getPlusVus() {
        Query query = em.createQuery("SELECT p FROM Produit p ORDER BY p.nbvues DESC");
        query.setMaxResults(6);
        return (List<Produit>) query.getResultList();
    }
    
    public ProduitView findViewById(Integer id) {
        return em.find(ProduitView.class, id);
    }
    public List<ProduitView> getProduitByCategorie(Integer idCategorie){
        Query q = em.createQuery("select p from ProduitView p where p.categorieId = :idcategorie order by p.dateajout desc, p.nbvues desc");
        q.setParameter("idcategorie", idCategorie);
        q.setMaxResults(5);
        return q.getResultList();
    }
}
