/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import ejb.AbonneBean;
import ejb.ClientBean;
import ejb.MagasinBean;
import entity.Client;
import entity.Favori;
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
@Named(value = "magasinMB")
@RequestScoped
public class MagasinMB {

    @EJB
    private AbonneBean abonneBean;

    @EJB
    private ClientBean clientBean;

    @EJB
    private MagasinBean magasinBean;

    
    
    /**
     * Creates a new instance of MagasinMB
     */
    
    private Magasin magasin=new Magasin();

    private List<Magasin> listeMagasin;

    public List<Magasin> getListeMagasin() {
        if(listeMagasin==null){
            listeMagasin=magasinBean.findAll();
        }
        return listeMagasin;
    }

    public void setListeMagasin(List<Magasin> listeMagasin) {
        this.listeMagasin = listeMagasin;
    }
    
    
    
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
     
    private List<Favori> listeFavori;

    public List<Favori> getListeFavori() {
        if(listeFavori==null){
             FacesContext context = FacesContext.getCurrentInstance();
            Magasin a = (Magasin) context.getExternalContext().getSessionMap().get("magasinSession");
            listeFavori=abonneBean.getAbonne(a.getId());
        }
        return listeFavori;
    }

    public void setListeFavori(List<Favori> listeFavori) {
        this.listeFavori = listeFavori;
    }
    
    
}
