/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author misa
 */
@Entity
@Table(name = "MAGASIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Magasin.findAll", query = "SELECT m FROM Magasin m")
    , @NamedQuery(name = "Magasin.findById", query = "SELECT m FROM Magasin m WHERE m.id = :id")
    , @NamedQuery(name = "Magasin.findByNom", query = "SELECT m FROM Magasin m WHERE m.nom = :nom")
    , @NamedQuery(name = "Magasin.findByDescription", query = "SELECT m FROM Magasin m WHERE m.description = :description")
    , @NamedQuery(name = "Magasin.findByPhoto", query = "SELECT m FROM Magasin m WHERE m.photo = :photo")
    , @NamedQuery(name = "Magasin.findByAdresse", query = "SELECT m FROM Magasin m WHERE m.adresse = :adresse")
    , @NamedQuery(name = "Magasin.findByNumero", query = "SELECT m FROM Magasin m WHERE m.numero = :numero")
    , @NamedQuery(name = "Magasin.findByEmail", query = "SELECT m FROM Magasin m WHERE m.email = :email")
    , @NamedQuery(name = "Magasin.findByLogin", query = "SELECT m FROM Magasin m WHERE m.login = :login")
    , @NamedQuery(name = "Magasin.findByMdp", query = "SELECT m FROM Magasin m WHERE m.mdp = :mdp")
    , @NamedQuery(name = "Magasin.findByLongitude", query = "SELECT m FROM Magasin m WHERE m.longitude = :longitude")
    , @NamedQuery(name = "Magasin.findByLatitude", query = "SELECT m FROM Magasin m WHERE m.latitude = :latitude")})
public class Magasin implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 50)
    @Column(name = "PHOTO")
    private String photo;
    @Size(max = 50)
    @Column(name = "ADRESSE")
    private String adresse;
    @Size(max = 50)
    @Column(name = "NUMERO")
    private String numero;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 50)
    @Column(name = "LOGIN")
    private String login;
    @Size(max = 50)
    @Column(name = "MDP")
    private String mdp;
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Column(name = "LATITUDE")
    private Double latitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasinId")
    private transient List<Produit> produitList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasinId")
    private transient List<CategorieMagasin> categorieMagasinList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasinId")
    private transient List<Favori> favoriList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasinId")
    private transient List<EvenementMagasin> evenementMagasinList;

    public Magasin() {
    }

    public Magasin(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @XmlTransient
    public List<Produit> getProduitList() {
        return produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        this.produitList = produitList;
    }

    @XmlTransient
    public List<CategorieMagasin> getCategorieMagasinList() {
        return categorieMagasinList;
    }

    public void setCategorieMagasinList(List<CategorieMagasin> categorieMagasinList) {
        this.categorieMagasinList = categorieMagasinList;
    }

    @XmlTransient
    public List<Favori> getFavoriList() {
        return favoriList;
    }

    public void setFavoriList(List<Favori> favoriList) {
        this.favoriList = favoriList;
    }

    @XmlTransient
    public List<EvenementMagasin> getEvenementMagasinList() {
        return evenementMagasinList;
    }

    public void setEvenementMagasinList(List<EvenementMagasin> evenementMagasinList) {
        this.evenementMagasinList = evenementMagasinList;
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
        if (!(object instanceof Magasin)) {
            return false;
        }
        Magasin other = (Magasin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Magasin[ id=" + id + " ]";
    }
    
}
