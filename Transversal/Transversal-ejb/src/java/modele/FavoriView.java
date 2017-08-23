/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author misa
 */
public class FavoriView {
    private Integer id;
    private Integer idMagasin;
    private Integer idClient;

    public FavoriView(Integer id, Integer idMagasin, Integer idClient) {
        this.id = id;
        this.idMagasin = idMagasin;
        this.idClient = idClient;
    }

    public FavoriView() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Integer idMagasin) {
        this.idMagasin = idMagasin;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }
    
    
}
