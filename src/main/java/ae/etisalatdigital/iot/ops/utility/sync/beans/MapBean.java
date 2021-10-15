/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author appadmin
 */
@Named("mapBean")
public class MapBean {
    
    private MapModel model = new DefaultMapModel();
    
    public MapBean() {
        model.addOverlay(new Marker(new LatLng(36.879466, 30.667648), "Cureent Location"));
        
    }
    public MapModel getModel() { 
        return this.model; 
    }
    
    public void save(){
        
    }
}
