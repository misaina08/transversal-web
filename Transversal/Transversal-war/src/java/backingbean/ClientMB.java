/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import ejb.ClientBean;
import entity.Client;
import entity.EvenementClient;
import entity.EvenementMagasin;
import entity.Magasin;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kiashi
 */
@Named(value = "clientMB")
@RequestScoped
public class ClientMB {

    @EJB
    private ClientBean clientBean;

    
    /**
     * Creates a new instance of ClientMB
     */
    public ClientMB() {
    }
    private Client client = new Client();

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public String connectClient() {
        Client huhu = clientBean.findByLogin(client.getLogin(), client.getMdp());
        System.out.println("_____________"+client.getLogin());
        System.out.println("_____________"+client.getMdp());
        if (huhu != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("clientSession", huhu);
            System.out.println("connecter________"+huhu.getNom());
            return "/Front/accueil?faces-redirect=true";
        } else {
            return "/Front/login?faces-redirect=true";
        }
    }
    
    private List<Magasin> listefavori;

    public List<Magasin> getListefavori() {
        if(listefavori==null){
            listefavori=clientBean.getFavorie();
        }
        return listefavori;
    }

    public void setListefavori(List<Magasin> listefavori) {
        this.listefavori = listefavori;
    }
    
    private List<EvenementClient> listeMesEvenement;

    public List<EvenementClient> getListeMesEvenement() {
        if(listeMesEvenement==null){
            listeMesEvenement=clientBean.getEvenement();
        }
        return listeMesEvenement;
    }

    public void setListeMesEvenement(List<EvenementClient> listeMesEvenement) {
        this.listeMesEvenement = listeMesEvenement;
    }

   
    
}
