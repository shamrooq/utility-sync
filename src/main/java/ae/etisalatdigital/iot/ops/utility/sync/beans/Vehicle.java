/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans;

import java.io.Serializable;

/**
 *
 * @author au_mobility
 */
public class Vehicle implements Serializable{
  
    
    private int ID;
    private String vehicleNumberPlate;
    private String vehicleModel;
    private Long bomId;
    
    public Vehicle(){}
    
    public Vehicle(int ID, String vehicleNumberPlate, String vehicleModel, Long bomId){
        this.ID = ID;
        this.vehicleModel = vehicleModel;
        this.vehicleNumberPlate = vehicleNumberPlate;
        this.bomId = bomId;
    }
    
    
    public int getID() {
        return ID;
    }

    public String getVehicleNumberPlate() {
        return vehicleNumberPlate;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public Long getBomId() {
        return bomId;
    }
    
    

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setVehicleNumberPlate(String vehicleNumberPlate) {
        this.vehicleNumberPlate = vehicleNumberPlate;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    } 

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }
    
    
    
}
