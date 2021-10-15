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
public class SimCard implements Serializable{ 
    
    private int simID;
    private String simNumber;
    private String simCardNumber;
    private String simIMEI;
    
    public SimCard(){}

    public int getSimID() {
        return simID;
    }

    public String getSimNumber() {
        return simNumber;
    }

    public String getSimCardNumber() {
        return simCardNumber;
    }

    public String getSimIMEI() {
        return simIMEI;
    }

    public void setSimID(int simID) {
        this.simID = simID;
    }

    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }

    public void setSimCardNumber(String simCardNumber) {
        this.simCardNumber = simCardNumber;
    }

    public void setSimIMEI(String simIMEI) {
        this.simIMEI = simIMEI;
    }
    
    
    
    
}
