/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Client;
import entity.EvenementClient;
import entity.Favori;
import entity.Magasin;
import entity.Favori;
import entity.Magasin;
import entity.ProduitView;
import java.util.ArrayList;
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
public class ClientBean {

    @PersistenceContext(unitName = "Transversal-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Client findByLogin(String login, String mdp){
        Query query = em.createQuery("SELECT c FROM Client c WHERE c.login = :login and c.mdp = :mdp");
        query.setParameter("login", login);
        query.setParameter("mdp", mdp);
        return (Client) query.getSingleResult();
    }
    
    public Client findById(Integer id) {
        Query cl = em.createNamedQuery("Client.findById");
        cl.setParameter("id", id);
        return (Client) cl.getSingleResult();
    }
    
    
     public void ajoutClient(Client Client) {
        em.persist(Client);
    }

    public void ajoutFavori(Favori favori) {
        em.persist(favori);
    }
    
    public List<Magasin> getFavorie() {
        FacesContext context = FacesContext.getCurrentInstance();
        Client a = (Client) context.getExternalContext().getSessionMap().get("clientSession");
        Query q = em.createQuery("select p from Favori p WHERE p.clientId.id = :id");
        q.setParameter("id", a.getId());
        return (List<Magasin>)q.getResultList();
    }
    
     public List<EvenementClient> getEvenement() {
        FacesContext context = FacesContext.getCurrentInstance();
        Client a = (Client) context.getExternalContext().getSessionMap().get("clientSession");
        Query q = em.createQuery("select p from EvenementClient p WHERE p.clientId.id = :id");
        q.setParameter("id", a.getId());
        return (List<EvenementClient>)q.getResultList();
    }
     
     public List<EvenementClient> getEvenementByClient(Integer a) {
        Query q = em.createQuery("select p from EvenementClient p WHERE p.clientId.id = :id");
        q.setParameter("id", a);
        return (List<EvenementClient>)q.getResultList();
    }
     
     
     public void ajoutEvenementClietn(EvenementClient EvenementClient) {
        em.persist(EvenementClient);
    }

    public void abonner(Integer idclient, Integer idmagasin) throws Exception {
        try {
            Favori favori = new Favori();
            Client c = new Client();
            c.setId(idclient);
            Magasin m = new Magasin();
            m.setId(idmagasin);
            favori.setClientId(c);
            favori.setMagasinId(m);

            em.persist(favori);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Magasins favoris d'un client
     *
     * @param idclient
     * @return
     */
    public List<Magasin> getMagasinsFavoris(Integer idclient) {
        Query q = em.createQuery("select f from Favori f where f.clientId.id = :idclient");
        q.setParameter("idclient", idclient);
        List<Favori> listeFavoris = q.getResultList();
        List<Magasin> result = new ArrayList<Magasin>(listeFavoris.size());
        for (Favori favori : listeFavoris) {
            result.add(favori.getMagasinId());
        }
        return result;
    }

    /**
     * les produits ajoutés récemments par les magasins favoris d'un client
     *
     * @param idclient
     * @return
     */
    public List<ProduitView> getProduitsAleatoireMagFavoris(Integer idclient) {
        String whereQuery = "";
        List<Magasin> favoris = getMagasinsFavoris(idclient);
        int i = 0;
        for (Magasin magasin : favoris) {
            whereQuery += " p.magasinId = "+magasin.getId();
            if (i < favoris.size() - 2) {
                whereQuery += " or ";
            }
            i++;
        }
        if (favoris.size() != 0) {
            whereQuery = " where " + whereQuery;
        }
        Query query = em.createQuery("select p from ProduitView p " + whereQuery);
        return query.getResultList();
    }
}
