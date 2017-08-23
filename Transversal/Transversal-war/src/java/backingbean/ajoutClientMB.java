/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import ejb.ClientBean;
import entity.Client;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author kiashi
 */
@Named(value = "ajoutClientMB")
@RequestScoped
public class ajoutClientMB {

    @EJB
    private ClientBean clientBean;

    
    /**
     * Creates a new instance of ajoutClientMB
     */
    public ajoutClientMB() {
    }
    private Client client= new Client();

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public String ajouterClient(){
        clientBean.ajoutClient(client);
        return "/Front/accueil?faces-redirect=true";
    }
}
