/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
/**
 *
 * @author kiashi
 */
@Named(value = "markersView")
@RequestScoped
public class MarkersView {

    /**
     * Creates a new instance of MarkersView
     */
    private MapModel simpleModel;
  
    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
          
        //Shared coordinates
        LatLng coord1 = new LatLng(-18.902205, 47.527611);
        
          
        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Apple Store Behoririka"));
        
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    public MarkersView() {
    }
    
}
