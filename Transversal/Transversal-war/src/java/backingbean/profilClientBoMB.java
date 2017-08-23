/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import ejb.ClientBean;
import entity.Client;
import entity.EvenementClient;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author kiashi
 */
@Named(value = "profilClientBoMB")
@RequestScoped
public class profilClientBoMB {

    @EJB
    private ClientBean clientBean;

    /**
     * Creates a new instance of profilClientBoMB
     */
    
    public profilClientBoMB() {
    }
    private int idClient;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    private Client client = new Client();

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
     public void loadClient() {
        this.client = clientBean.findById(new Integer(idClient));
    }
    
     private List<EvenementClient> listeEvenement;

    public List<EvenementClient> getListeEvenement() {
        if(listeEvenement==null){
            listeEvenement=clientBean.getEvenementByClient(idClient);
        }
        return listeEvenement;
    }

    public void setListeEvenement(List<EvenementClient> listeEvenement) {
        this.listeEvenement = listeEvenement;
    }
     
}
