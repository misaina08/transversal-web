/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import ejb.ClientBean;
import entity.EvenementClient;
import entity.Client;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kiashi
 */
@Named(value = "evenementClientMB")
@RequestScoped
public class EvenementClientMB {

    @EJB
    private ClientBean clientBean;

    /**
     * Creates a new instance of EvenementClientMB
     */
    private EvenementClient EvClient = new EvenementClient();

    public EvenementClient getEvClient() {
        return EvClient;
    }

    public void setEvClient(EvenementClient EvClient) {
        this.EvClient = EvClient;
    }
    
    public EvenementClientMB() {
    }
    
    public String ajouterEvenementClient() {
        FacesContext context = FacesContext.getCurrentInstance();
        Client a = (Client) context.getExternalContext().getSessionMap().get("clientSession");
        EvClient.setClientId(a);
        clientBean.ajoutEvenementClietn(EvClient);

        
        return "/Front/profil?faces-redirect=true";
    }

    
}
