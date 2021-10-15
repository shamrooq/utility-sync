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
public class OPDevice implements Serializable{
    
    private int ID;
    private String deviceName;
    private String deviceType;
    
    public OPDevice(){}
    
    public OPDevice (int ID, String deviceName, String deviceType){
        this.ID = ID;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
    }

    public int getID() {
        return ID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    
    
    
}
