/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author misa
 */
@Entity
@Table(name = "PRODUIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
    , @NamedQuery(name = "Produit.findById", query = "SELECT p FROM Produit p WHERE p.id = :id")
    , @NamedQuery(name = "Produit.findByDesignation", query = "SELECT p FROM Produit p WHERE p.designation = :designation")
    , @NamedQuery(name = "Produit.findByPrix", query = "SELECT p FROM Produit p WHERE p.prix = :prix")
    , @NamedQuery(name = "Produit.findByQuantiteenstock", query = "SELECT p FROM Produit p WHERE p.quantiteenstock = :quantiteenstock")
    , @NamedQuery(name = "Produit.findByUnite", query = "SELECT p FROM Produit p WHERE p.unite = :unite")
    , @NamedQuery(name = "Produit.findByDateajout", query = "SELECT p FROM Produit p WHERE p.dateajout = :dateajout")
    , @NamedQuery(name = "Produit.findByPhoto", query = "SELECT p FROM Produit p WHERE p.photo = :photo")
    , @NamedQuery(name = "Produit.findByTag", query = "SELECT p FROM Produit p WHERE p.tag = :tag")
    , @NamedQuery(name = "Produit.findByNbvues", query = "SELECT p FROM Produit p WHERE p.nbvues = :nbvues")})
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "DESIGNATION")
    private String designation;
    @Column(name = "PRIX")
    private Double prix;
    @Column(name = "QUANTITEENSTOCK")
    private Double quantiteenstock;
    @Size(max = 50)
    @Column(name = "UNITE")
    private String unite;
    @Column(name = "DATEAJOUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateajout;
    @Size(max = 50)
    @Column(name = "PHOTO")
    private String photo;
    @Size(max = 1000)
    @Column(name = "TAG")
    private String tag;
    @Column(name = "NBVUES")
    private Integer nbvues;
    @JoinColumn(name = "CATEGORIE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Categorie categorieId;
    @JoinColumn(name = "MAGASIN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Magasin magasinId;

    public Produit() {
    }

    public Produit(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getQuantiteenstock() {
        return quantiteenstock;
    }

    public void setQuantiteenstock(Double quantiteenstock) {
        this.quantiteenstock = quantiteenstock;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Date getDateajout() {
        return dateajout;
    }

    public void setDateajout(Date dateajout) {
        this.dateajout = dateajout;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getNbvues() {
        return nbvues;
    }

    public void setNbvues(Integer nbvues) {
        this.nbvues = nbvues;
    }

    public Categorie getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Categorie categorieId) {
        this.categorieId = categorieId;
    }

    public Magasin getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(Magasin magasinId) {
        this.magasinId = magasinId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Produit[ id=" + id + " ]";
    }
    
}
