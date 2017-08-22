/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import ejb.ProduitBean;
import entity.Produit;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author kiashi
 */
@Named(value = "filactuMB")
@RequestScoped
public class FilactuMB {

    @EJB
    private ProduitBean produitBean;

    
    /**
     * Creates a new instance of FilactuMB
     */
    
    
    
    private List<Produit> produitsRecents;
    private List<Produit> produitsPopulaires;
    
    public List<Produit> getProduitsRecents() {
        if(produitsRecents == null){
            produitsRecents=produitBean.getDerniersAjout();
        }
        return produitsRecents;
    }

    public void setProduitsRecents(List<Produit> produitsRecents) {
        this.produitsRecents = produitsRecents;
    }

    public List<Produit> getProduitsPopulaires() {
        if(produitsPopulaires ==  null){
            produitsPopulaires=produitBean.getPlusVus();
        }
        return produitsPopulaires;
    }

    public void setProduitsPopulaires(List<Produit> produitsPopulaires) {
        this.produitsPopulaires = produitsPopulaires;
    }
    
    public FilactuMB() {
    }
    
}
