/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import ejb.RechercheBean;
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
@Path("/recherche")
public class RechercheRes {

    @EJB
    private RechercheBean rechercheBean;
    
    @GET
    @Path("/{search}")
    @Produces("application/json")
    public List<ProduitView> rechercherSimple(@PathParam("search") String search){
        return rechercheBean.rechercher(search);
    }
}
