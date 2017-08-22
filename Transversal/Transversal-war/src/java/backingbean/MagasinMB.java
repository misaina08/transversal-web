/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import ejb.MagasinBean;
import entity.Magasin;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kiashi
 */
@Named(value = "magasinMB")
@RequestScoped
public class MagasinMB {

    @EJB
    private MagasinBean magasinBean;

    /**
     * Creates a new instance of MagasinMB
     */
    
    private Magasin magasin=new Magasin();

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }
    
    
    public MagasinMB() {
    }
    
     public String connectMagasin() {
        Magasin mag = magasinBean.findUtilisateur(magasin.getLogin(), magasin.getMdp());

        if (mag != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("magasinSession", mag);
            return "/Back/accueil?faces-redirect=true";
        } else {
            return "/Back/login?faces-redirect=true";
        }
    }
    
     
    public String logout() {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    return "/Front/accueil?faces-redirect=true";
} 
     
}
