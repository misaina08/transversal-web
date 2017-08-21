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
@Table(name = "EVENEMENT_MAGASIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvenementMagasin.findAll", query = "SELECT e FROM EvenementMagasin e")
    , @NamedQuery(name = "EvenementMagasin.findById", query = "SELECT e FROM EvenementMagasin e WHERE e.id = :id")
    , @NamedQuery(name = "EvenementMagasin.findByDatydebut", query = "SELECT e FROM EvenementMagasin e WHERE e.datydebut = :datydebut")
    , @NamedQuery(name = "EvenementMagasin.findByDatyfin", query = "SELECT e FROM EvenementMagasin e WHERE e.datyfin = :datyfin")
    , @NamedQuery(name = "EvenementMagasin.findByLibelle", query = "SELECT e FROM EvenementMagasin e WHERE e.libelle = :libelle")
    , @NamedQuery(name = "EvenementMagasin.findByDescription", query = "SELECT e FROM EvenementMagasin e WHERE e.description = :description")})
public class EvenementMagasin implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATYDEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datydebut;
    @Column(name = "DATYFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datyfin;
    @Size(max = 50)
    @Column(name = "LIBELLE")
    private String libelle;
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "MAGASIN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Magasin magasinId;

    public EvenementMagasin() {
    }

    public EvenementMagasin(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatydebut() {
        return datydebut;
    }

    public void setDatydebut(Date datydebut) {
        this.datydebut = datydebut;
    }

    public Date getDatyfin() {
        return datyfin;
    }

    public void setDatyfin(Date datyfin) {
        this.datyfin = datyfin;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof EvenementMagasin)) {
            return false;
        }
        EvenementMagasin other = (EvenementMagasin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EvenementMagasin[ id=" + id + " ]";
    }
    
}
