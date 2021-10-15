/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans;

/**
 *
 * @author au_mobility
 */
public class Estimation {
    
    private int siID;
    private String metersTotal;
    private String metersType;
    private String gatewaysTypeProposed;
    private int gatewaysRequired;
    private int metersPerGateway;
    private String EstimatedCableLength;
    
    public Estimation(){}

    public Estimation(int siID, String metersTotal, String metersType, String gatewaysTypeProposed, int gatewaysRequired, int metersPerGateway, String EstimatedCableLength) {
        this.siID = siID;
        this.metersTotal = metersTotal;
        this.metersType = metersType;
        this.gatewaysTypeProposed = gatewaysTypeProposed;
        this.gatewaysRequired = gatewaysRequired;
        this.metersPerGateway = metersPerGateway;
        this.EstimatedCableLength = EstimatedCableLength;
    }

    public int getSiID() {
        return siID;
    }

    public String getMetersTotal() {
        return metersTotal;
    }

    public String getMetersType() {
        return metersType;
    }

    public String getGatewaysTypeProposed() {
        return gatewaysTypeProposed;
    }

    public int getGatewaysRequired() {
        return gatewaysRequired;
    }

    public int getMetersPerGateway() {
        return metersPerGateway;
    }

    public String getEstimatedCableLength() {
        return EstimatedCableLength;
    }
    
    /*
    *
    */

    public void setSiID(int siID) {
        this.siID = siID;
    }

    public void setMetersTotal(String metersTotal) {
        this.metersTotal = metersTotal;
    }

    public void setMetersType(String metersType) {
        this.metersType = metersType;
    }

    public void setGatewaysTypeProposed(String gatewaysTypeProposed) {
        this.gatewaysTypeProposed = gatewaysTypeProposed;
    }

    public void setGatewaysRequired(int gatewaysRequired) {
        this.gatewaysRequired = gatewaysRequired;
    }

    public void setMetersPerGateway(int metersPerGateway) {
        this.metersPerGateway = metersPerGateway;
    }

    public void setEstimatedCableLength(String EstimatedCableLength) {
        this.EstimatedCableLength = EstimatedCableLength;
    }
    
    
    
    
}
