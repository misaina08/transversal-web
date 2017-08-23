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
        return (Client)query.getSingleResult();
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
}
