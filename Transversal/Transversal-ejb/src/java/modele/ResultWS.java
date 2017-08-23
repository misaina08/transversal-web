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
public class ResultWS {
    private Integer res;

    public ResultWS(){};
    public ResultWS(Integer res){
        this.res = res;
    }
    
    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }
    
}
