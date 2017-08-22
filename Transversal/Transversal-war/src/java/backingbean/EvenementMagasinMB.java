/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import ejb.EvenementMagasinBean;
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
@Named(value = "evenementMagasinMB")
@RequestScoped
public class EvenementMagasinMB {

    @EJB
    private EvenementMagasinBean evenementMagasinBean;

    /**
     * Creates a new instance of EvenementMagasinMB
     */
    
    public EvenementMagasinMB() {
    }
    
    FacesContext context = FacesContext.getCurrentInstance();
    private Magasin a = (Magasin) context.getExternalContext().getSessionMap().get("magasinSession");
    
    private List<EvenementMagasin> listevenement;
    private EvenementMagasin evenement=new EvenementMagasin();

    public Magasin getA() {
        return a;
    }

    public void setA(Magasin a) {
        this.a = a;
    }

    public List<EvenementMagasin> getListevenement() {
        if(listevenement == null){
            listevenement=evenementMagasinBean.getListEvenement();
        }
        return listevenement;
    }

    public void setListevenement(List<EvenementMagasin> listevenement) {
        this.listevenement = listevenement;
    }
    
    public String ajouterEvenementMagasin() {
        FacesContext context = FacesContext.getCurrentInstance();
        Magasin a = (Magasin) context.getExternalContext().getSessionMap().get("magasinSession");
        evenement.setMagasinId(a);
        System.out.println("_____________"+evenement.getDescription());
        System.out.println("_____________"+evenement.getLibelle());
        System.out.println("_____________"+evenement.getMagasinId().getNom());
        System.out.println("_____________"+evenement.getDatydebut());
        evenementMagasinBean.ajoutEvenement(evenement);

        
        return "/Back/listeevenement?faces-redirect=true";
    }

    public EvenementMagasin getEvenement() {
        return evenement;
    }

    public void setEvenement(EvenementMagasin evenement) {
        this.evenement = evenement;
    }
    
}
