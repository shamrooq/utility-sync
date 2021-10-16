/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

/**
 *
 * @author appadmin
 */
public class BOMGatewayEstDTO {
    
    private Long id;
    private Long bomId; 
    private String gatewaysType;
    private String gatewaysTypeProposed;
    private int gatewaysRequired;
    private int metersPerGateway;
    private String EstimatedCableLength;
    
    
    
    
    private String gatewaysVendor;
    private String gatewaysLocation;
    private String gatewaysDaisychain;
    private String gatewaysChainLabel;
    
    
    private String serialNumber;
    private String simICCID; 
    
    
   
    private Boolean powerIntruption;
    private Long signalStrength;
    private String signalStrengthIndicator;
    
    private Boolean antenaRequired;
    
    private Long gatewayRoomId;
    private Long gatewayFloorId;
    
    private Double cableLength; 
    
    public BOMGatewayEstDTO() {
    }

    /*
    public BOMGatewayEstDTO(Long id, Long bomId, String gatewaysType, String gatewaysTypeProposed, int gatewaysRequired, int metersPerGateway, String EstimatedCableLength) {
        this.id = id;
        this.bomId = bomId;
        this.gatewaysType = gatewaysType;
        this.gatewaysTypeProposed = gatewaysTypeProposed;
        this.gatewaysRequired = gatewaysRequired;
        this.metersPerGateway = metersPerGateway;
        this.EstimatedCableLength = EstimatedCableLength;
    }

    public BOMGatewayEstDTO(Long id, Long bomId, String gatewaysType, String gatewaysTypeProposed, int gatewaysRequired, int metersPerGateway, String EstimatedCableLength, String gatewaysVendor, String gatewaysLocation, String gatewaysDaisychain, String gatewaysChainLabel) {
        this.id = id;
        this.bomId = bomId;
        this.gatewaysType = gatewaysType;
        this.gatewaysTypeProposed = gatewaysTypeProposed;
        this.gatewaysRequired = gatewaysRequired;
        this.metersPerGateway = metersPerGateway;
        this.EstimatedCableLength = EstimatedCableLength;
        
        this.gatewaysVendor = gatewaysVendor;
        this.gatewaysLocation = gatewaysLocation;
        this.gatewaysDaisychain = gatewaysDaisychain;
        this.gatewaysChainLabel = gatewaysChainLabel;
    }
    */
    
    public BOMGatewayEstDTO(Long id, Long bomId, String gatewaysType, String gatewaysTypeProposed, int gatewaysRequired, int metersPerGateway, String EstimatedCableLength, String gatewaysVendor, String gatewaysLocation, String gatewaysDaisychain, String gatewaysChainLabel, String serialNumber, String simICCID, Boolean powerIntruption, Long signalStrength, String signalStrengthIndicator, Boolean antenaRequired, Long gatewayRoomId, Long gatewayFloorId, Double cableLength) {
        this.id = id;
        this.bomId = bomId;
        this.gatewaysType = gatewaysType;
        this.gatewaysTypeProposed = gatewaysTypeProposed;
        this.gatewaysRequired = gatewaysRequired;
        this.metersPerGateway = metersPerGateway;
        this.EstimatedCableLength = EstimatedCableLength;
        this.gatewaysVendor = gatewaysVendor;
        this.gatewaysLocation = gatewaysLocation;
        this.gatewaysDaisychain = gatewaysDaisychain;
        this.gatewaysChainLabel = gatewaysChainLabel;
        this.serialNumber = serialNumber;
        this.simICCID = simICCID;
        this.powerIntruption = powerIntruption;
        this.signalStrength = signalStrength;
        this.signalStrengthIndicator = signalStrengthIndicator;
        this.antenaRequired = antenaRequired;
        this.gatewayFloorId = gatewayFloorId;
        this.gatewayRoomId = gatewayRoomId;
        this.cableLength = cableLength; 
    }
    
    
    
    
    

    public Long getId() {
        return id;
    }

    public Long getBomId() {
        return bomId;
    }

    public String getGatewaysType() {
        return gatewaysType;
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


    public String getGatewaysVendor() {
        return gatewaysVendor;
    }

    public String getGatewaysLocation() {
        return gatewaysLocation;
    }

    public String getGatewaysDaisychain() {
        return gatewaysDaisychain;
    }

    public String getGatewaysChainLabel() {
        return gatewaysChainLabel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getSimICCID() {
        return simICCID;
    }

    public Boolean getPowerIntruption() {
        return powerIntruption;
    }

    public Long getSignalStrength() {
        return signalStrength;
    }

    public String getSignalStrengthIndicator() {
        return signalStrengthIndicator;
    }

    public Boolean getAntenaRequired() {
        return antenaRequired;
    }

    public Long getGatewayRoomId() {
        return gatewayRoomId;
    }

    public Long getGatewayFloorId() {
        return gatewayFloorId;
    }

    public Double getCableLength() {
        return cableLength;
    }
    
    
    
    

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public void setGatewaysType(String gatewaysType) {
        this.gatewaysType = gatewaysType;
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


    public void setGatewaysVendor(String gatewaysVendor) {
        this.gatewaysVendor = gatewaysVendor;
    }

    public void setGatewaysLocation(String gatewaysLocation) {
        this.gatewaysLocation = gatewaysLocation;
    }

    public void setGatewaysDaisychain(String gatewaysDaisychain) {
        this.gatewaysDaisychain = gatewaysDaisychain;
    }

    public void setGatewaysChainLabel(String gatewaysChainLabel) {
        this.gatewaysChainLabel = gatewaysChainLabel;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setSimICCID(String simICCID) {
        this.simICCID = simICCID;
    }

    public void setPowerIntruption(Boolean powerIntruption) {
        this.powerIntruption = powerIntruption;
    }

    public void setSignalStrength(Long signalStrength) {
        this.signalStrength = signalStrength;
    }

    public void setSignalStrengthIndicator(String signalStrengthIndicator) {
        this.signalStrengthIndicator = signalStrengthIndicator;
    }

    public void setAntenaRequired(Boolean antenaRequired) {
        this.antenaRequired = antenaRequired;
    }

    public void setGatewayRoomId(Long gatewayRoomId) {
        this.gatewayRoomId = gatewayRoomId;
    }

    public void setGatewayFloorId(Long gatewayFloorId) {
        this.gatewayFloorId = gatewayFloorId;
    }

    public void setCableLength(Double cableLength) {
        this.cableLength = cableLength;
    }
    
    
    
    
    public void handleKeyEvent() {
        if(signalStrength <= -111){
            signalStrengthIndicator = "LOW";
        }else if(signalStrength <= -101){
            signalStrengthIndicator = "MEDIUM";
        }else{
            signalStrengthIndicator = "HIGH";
        }
        
    }
}
