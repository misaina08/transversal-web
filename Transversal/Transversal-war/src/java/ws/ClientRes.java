/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import ejb.ClientBean;
import entity.Client;
import entity.Magasin;
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
@Path("/clients")
public class ClientRes {

    @EJB
    private ClientBean clientBean;
    
    @GET
    @Path("/connexion/{login}/{mdp}")
    @Produces("application/json")
    public Client connect(@PathParam("login") String login, @PathParam("mdp") String mdp){
        return clientBean.findByLogin(login, mdp);
    }
    
    @GET
    @Path("/{idclient}/abonnements")
    @Produces("application/json")
    public List<ProduitView> getProduitsAbonnes(@PathParam("idclient") Integer idclient){
        return clientBean.getProduitsAleatoireMagFavoris(idclient);
    }
    
    @GET
    @Path("/{idclient}/abonnements/favoris")
    @Produces("application/json")
    public List<Magasin> getMagasinsFavoris(@PathParam("idclient") Integer idclient){
        return clientBean.getMagasinsFavoris(idclient);
    }
}
