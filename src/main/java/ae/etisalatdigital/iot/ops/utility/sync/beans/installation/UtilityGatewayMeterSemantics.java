/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans.installation;

import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.view.ViewScoped;

/**
 *
 * @author shamr
 */
@ViewScoped
public class UtilityGatewayMeterSemantics implements Serializable{
    private String utilityNumber;
    private Map<Long, List<BOMGatewayEstDTO>> gatewayFloors;
    private Set<BOMGatewayEstDTO> gatewaySet;
    private Set<BOMMeterDTO> meterList;
    private Map<BigInteger,Set<BOMMeterDTO>> gtwMeterMap;//list with key as gatewayId and value as a list of meters
    
    public UtilityGatewayMeterSemantics(){
        
    }
    
    public UtilityGatewayMeterSemantics(String utilityNumber){
        this.utilityNumber = utilityNumber;
    }

    public String getUtilityNumber() {
        return utilityNumber;
    }

    public void setUtilityNumber(String utilityNumber) {
        this.utilityNumber = utilityNumber;
    }

    public Set<BOMGatewayEstDTO> getGatewaySet() {
        return gatewaySet;
    }

    public void setGatewaySet(Set<BOMGatewayEstDTO> gatewaySet) {
        this.gatewaySet = gatewaySet;
    }

    public Set<BOMMeterDTO> getMeterList() {
        return meterList;
    }

    public void setMeterList(Set<BOMMeterDTO> meterList) {
        this.meterList = meterList;
    }

    public Map<BigInteger, Set<BOMMeterDTO>> getGtwMeterMap() {
        return gtwMeterMap;
    }

    public void setGtwMeterMap(Map<BigInteger, Set<BOMMeterDTO>> gtwMeterMap) {
        this.gtwMeterMap = gtwMeterMap;
    }

    public Map<Long, List<BOMGatewayEstDTO>> getGatewayFloors() {
        return gatewayFloors;
    }

    public void setGatewayFloors(Map<Long, List<BOMGatewayEstDTO>> gatewayFloors) {
        this.gatewayFloors = gatewayFloors;
    }
}