/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import ejb.ProduitBean;
import entity.ProduitView;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


/**
 *
 * @author misa
 */
@ManagedBean
@Path("/produits")
public class ProduitRes {

    @EJB
    private ProduitBean produitBean;
    
    @GET
    @Path("/accueil")
    @Produces("application/json")
    public List<ProduitView> produitAccueil(){
        return produitBean.getRecent();
    }   
    
    @GET
    @Path("{idproduit}")
    @Produces("application/json")
    public ProduitView getById(@PathParam("idproduit") Integer idProduit){
        return produitBean.findViewById(idProduit);
    }
    
    @GET
    @Path("/similaires/{idcategorie}")
    @Produces("application/json")
    public List<ProduitView> getProduitsSimilaires(@PathParam("idcategorie") Integer idCategorie){
        return produitBean.getProduitByCategorie(idCategorie);
    }
}
