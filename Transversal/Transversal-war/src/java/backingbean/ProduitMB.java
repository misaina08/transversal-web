/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import ejb.CategorieBean;
import ejb.ClientBean;
import ejb.MagasinBean;
import ejb.ProduitBean;
import entity.Categorie;
import entity.Client;
import entity.EvenementMagasin;
import entity.Favori;
import entity.Magasin;
import entity.Produit;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author kiashi
 */
@Named(value = "produitMB")
@RequestScoped
public class ProduitMB {

    @EJB
    private ClientBean clientBean;

    @EJB
    private MagasinBean magasinBean;

    @EJB
    private CategorieBean categorieBean;

    @EJB
    private ProduitBean produitBean;

    
    /**
     * Creates a new instance of ProduitMB
     */
    FacesContext context = FacesContext.getCurrentInstance();
    private Magasin magasin = (Magasin) context.getExternalContext().getSessionMap().get("magasinSession");

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    private int idMagasin;
    
    public void loadMagasin() {
        this.magasin = magasinBean.findById(new Integer(idMagasin));
        
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }
    
    public List<Produit> getListeproduit() {
        if(listeproduit == null){
            listeproduit=produitBean.getListProduit();
        }
        return listeproduit;
    }

    public void setListeproduit(List<Produit> listeproduit) {
        this.listeproduit = listeproduit;
    }

   
    
    private List<Produit> listeproduit;
    private List<Produit> listeproduitMagasin;

    public List<Produit> getListeproduitMagasin() {
        if(listeproduitMagasin==null){
            listeproduitMagasin=produitBean.getList(idMagasin);
        }
        return listeproduitMagasin;
    }

    public void setListeproduitMagasin(List<Produit> listeproduitMagasin) {
        this.listeproduitMagasin = listeproduitMagasin;
    }
    
    private int idProduit;

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    
    
    public void loadProduit() {
        this.produit = produitBean.findById(new Integer(idProduit));
    }
    
    
    public ProduitMB() {
    }
    
    private Produit produit = new Produit();

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    public String ajouter() {
        produit.setCategorieId(categorieIdSelect);
        produitBean.ajoutProduit(produit);
        return "/Back/accueil?faces-redirect=true";
    }
    
    public String modifier() {
        FacesContext context = FacesContext.getCurrentInstance();
        Magasin a = (Magasin) context.getExternalContext().getSessionMap().get("magasinSession");
        this.produit = produitBean.findById(new Integer(idProduit));
        produit.setMagasinId(a);
        produit.setDateajout(new Date());
        produit.setDesignation(produit.getDesignation());
        produit = produitBean.update(produit);
        return "/Back/accueil?faces-redirect=true";
    }
    
    
   private List<Categorie> listecategorie;
   private Categorie categorieIdSelect;

    public Categorie getCategorieIdSelect() {
        return categorieIdSelect;
    }

    public void setCategorieIdSelect(Categorie categorieIdSelect) {
        this.categorieIdSelect = categorieIdSelect;
    }

    public List<Categorie> getListecategorie() {
        if(listecategorie == null){
            listecategorie = categorieBean.findAll();
        }
        return listecategorie;
    }

    public void setListecategorie(List<Categorie> listecategorie) {
        this.listecategorie = listecategorie;
    }

    public Converter getCategorieConverter() {
        return categorieConverter;
    }

    public void setCategorieConverter(Converter categorieConverter) {
        this.categorieConverter = categorieConverter;
    }

   
    
    private Converter categorieConverter = new Converter() {
        /**
         * Convertit une String en Magasin.
         *
         * @param value valeur à convertir
         */
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            return categorieBean.findById(new Integer(value));
        }

        /**
         * Convertit un Magasin en String.
         *
         * @param value valeur à convertir
         */
        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            Categorie catTarifaire = (Categorie) value;
            return catTarifaire.getId() + "";
        }
    };
    
    private List<Produit> produitSimilaire;

    public List<Produit> getProduitSimilaire() {
        if(produitSimilaire == null){
            produitSimilaire=produitBean.getSimilaire(produit);
        }
        return produitSimilaire;
    }

    public void setProduitSimilaire(List<Produit> produitSimilaire) {
        this.produitSimilaire = produitSimilaire;
    }

     private Favori favori = new Favori();
     public String ajoutFavMagasin() {
         System.out.println("hahahahahahahahaha______");
         this.magasin = magasinBean.findById(new Integer(idMagasin));
         FacesContext context = FacesContext.getCurrentInstance();
          Client huhu=(Client) context.getExternalContext().getSessionMap().get("clientSession");
          System.out.println("huhuhuhuhuhuhuu______");
         System.out.println("id______magasin"+magasin.getId());
         System.out.println("id______client"+huhu.getId());
         System.out.println("hohohohohohohoho______");
         favori.setClientId(huhu);
        favori.setMagasinId(magasin);
        clientBean.ajoutFavori(favori);
        return "/Front/listeFavorie?faces-redirect=true";
    }

    public Favori getFavori() {
        return favori;
    }

    public void setFavori(Favori favori) {
        this.favori = favori;
    }

   private List<EvenementMagasin> listeEvMagasin;

    public List<EvenementMagasin> getListeEvMagasin() {
        if(listeEvMagasin==null){
            this.magasin = magasinBean.findById(new Integer(idMagasin));
            listeEvMagasin=magasinBean.getEvenement(magasin);
        }
        return listeEvMagasin;
    }

    public void setListeEvMagasin(List<EvenementMagasin> listeEvMagasin) {
        this.listeEvMagasin = listeEvMagasin;
    }
   
}
