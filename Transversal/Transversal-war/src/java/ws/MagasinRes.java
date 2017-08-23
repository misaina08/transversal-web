/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import ejb.ClientBean;
import ejb.MagasinBean;
import entity.CategorieMagasinView;
import entity.Magasin;
import entity.ProduitView;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import modele.FavoriView;
import modele.ResultWS;

/**
 *
 * @author misa
 */
@ManagedBean
@Path("/magasins")
public class MagasinRes {

    @EJB
    private ClientBean clientBean;

    @EJB
    private MagasinBean magasinBean;
    

    @GET
    @Path("/{idmagasin}")
    @Produces("application/json")
    public Magasin getMagasinById(@PathParam("idmagasin") Integer idmagasin) {
        return magasinBean.findById(idmagasin);
    }

    @GET
    @Path("/{idmagasin}/meilleurs")
    @Produces("application/json")
    public List<ProduitView> getMeilleursMagasin(@PathParam("idmagasin") Integer idmagasin) {
        return magasinBean.findMeilleursMagasin(idmagasin);
    }

    @GET
    @Path("/{idmagasin}/categories")
    @Produces("application/json")
    public List<CategorieMagasinView> getCategoriesMagasin(@PathParam("idmagasin") Integer idmagasin) {
        return magasinBean.findCategoriesMagasin(idmagasin);
    }
    
    @GET
    @Path("/{idmagasin}/categories/{idcategorie}")
    @Produces("application/json")
    public List<ProduitView> getMeilleursMagasin(@PathParam("idmagasin") Integer idmagasin, 
            @PathParam("idcategorie") Integer idcategorie) {
        return magasinBean.findProduitsCategorieMagasin(idmagasin, idcategorie);
    }
    
    @POST
    @Path("/abonner")
    @Consumes("application/json")
    @Produces("application/json")
    public ResultWS abonner(FavoriView favori){
        try{
            clientBean.abonner(favori.getIdClient(), favori.getIdMagasin());
            return new ResultWS(1);
        }catch(Exception ex){
            return new ResultWS(0);
        }
    }
}
