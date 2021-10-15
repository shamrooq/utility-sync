/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author appadmin
 */
@ManagedBean(name = "gatewayItem")
@ViewScoped
public class BOMGatewayEstDTO implements Serializable,Comparable<BOMGatewayEstDTO>{
    private BigInteger id;
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
    private BigInteger simICCID;
    private Boolean powerIntruption;
    private Long signalStrength;
    private String signalStrengthIndicator;
    private String gatewayFloor;
    private String gatewayRoom;

    public BOMGatewayEstDTO() {
    }

    public BOMGatewayEstDTO(BigInteger id, Long bomId, String gatewaysType, String gatewaysTypeProposed, int gatewaysRequired, int metersPerGateway, String EstimatedCableLength) {
        this.id = id;
        this.bomId = bomId;
        this.gatewaysType = gatewaysType;
        this.gatewaysTypeProposed = gatewaysTypeProposed;
        this.gatewaysRequired = gatewaysRequired;
        this.metersPerGateway = metersPerGateway;
        this.EstimatedCableLength = EstimatedCableLength;
    }

    public BOMGatewayEstDTO(BigInteger id, Long bomId, String gatewaysType, String gatewaysTypeProposed, int gatewaysRequired, int metersPerGateway, String EstimatedCableLength, String gatewaysVendor, String gatewaysLocation, String gatewaysDaisychain, String gatewaysChainLabel,
            String serialNumber,BigInteger simICCID,String gatewayFloor,String gatewayRoom) {
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
        this.serialNumber=serialNumber;
        this.simICCID=simICCID;
        this.gatewayFloor=gatewayFloor;
        this.gatewayRoom=gatewayRoom;
    }


    public BigInteger getId() {
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

    public BigInteger getSimICCID() {
        return simICCID;
    }

    /**
     *
     * @param id
     */
    public void setId(BigInteger id) {
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

    public void setSimICCID(BigInteger simICCID) {
        this.simICCID = simICCID;
    }

    public Boolean getPowerIntruption() {
        return powerIntruption;
    }

    public void setPowerIntruption(Boolean powerIntruption) {
        this.powerIntruption = powerIntruption;
    }

    public Long getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(Long signalStrength) {
        this.signalStrength = signalStrength;
    }

    public String getSignalStrengthIndicator() {
        return signalStrengthIndicator;
    }

    public void setSignalStrengthIndicator(String signalStrengthIndicator) {
        this.signalStrengthIndicator = signalStrengthIndicator;
    }

    public String getGatewayFloor() {
        return gatewayFloor;
    }

    public void setGatewayFloor(String gatewayFloor) {
        this.gatewayFloor = gatewayFloor;
    }

    public String getGatewayRoom() {
        return gatewayRoom;
    }

    public void setGatewayRoom(String gatewayRoom) {
        this.gatewayRoom = gatewayRoom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BOMGatewayEstDTO other = (BOMGatewayEstDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    @Override
    public int compareTo(BOMGatewayEstDTO t) {
        return this.id.compareTo(t.getId());
    }

}
