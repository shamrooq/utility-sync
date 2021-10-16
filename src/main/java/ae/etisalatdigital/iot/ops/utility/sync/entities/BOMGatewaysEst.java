/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author appadmin
 */
@Entity
@Table(name = "BOM_Gateways_Est")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BOMGatewaysEst.findAll", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO(m.id,m.bomId,m.gatewaysType,m.gatewaysTypeProposed,m.gatewaysRequired,m.metersPerGateway,m.EstimatedCableLength, m.gatewaysVendor, m.gatewaysLocation, m.gatewaysDaisychain, m.gatewaysChainLabel, m.serialNumber, m.simICCID, m.powerIntruption, m.signalStrength, m.signalStrengthIndicator, m.antenaRequired, m.gatewayRoomId, m.gatewayFloorId, m.cableLength ) FROM BOMGatewaysEst m")
        ,@NamedQuery(name = "BOMGatewaysEst.findAllByBOMID", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO(m.id,m.bomId,m.gatewaysType,m.gatewaysTypeProposed,m.gatewaysRequired,m.metersPerGateway,m.EstimatedCableLength, m.gatewaysVendor, m.gatewaysLocation, m.gatewaysDaisychain, m.gatewaysChainLabel, m.serialNumber, m.simICCID, m.powerIntruption, m.signalStrength, m.signalStrengthIndicator, m.antenaRequired, m.gatewayRoomId, m.gatewayFloorId, m.cableLength ) FROM BOMGatewaysEst m where m.bomId = :bomId")
        , @NamedQuery(name = "BOMGatewaysEst.DELETE", query = "DELETE FROM BOMGatewaysEst m WHERE m.id = :id")
 })
public class BOMGatewaysEst implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EST_GTW_ID")
    private Long id;
    
    @Column(name = "BOM_ID")
    private Long bomId;
    
    @Column(name = "EST_BOM_GTW_Type")
    private String gatewaysType;
    @Column(name = "EST_GTW_Type")
    private String gatewaysTypeProposed;
    @Column(name = "CBL_Estimated")
    private String EstimatedCableLength;
    @Column(name = "EST_GTW_Count")
    private int gatewaysRequired;
    @Column(name = "MTRS_Per_GTW")
    private int metersPerGateway;
    
    @Column(name = "GTW_Vendor")
    private String gatewaysVendor;
    @Column(name = "GTW_Location")
    private String gatewaysLocation;
    @Column(name = "GTW_DaisyChain")
    private String gatewaysDaisychain;
    @Column(name = "GTW_ChainLabel")
    private String gatewaysChainLabel;
    
    @Column(name = "Serial_Number")
    private String serialNumber;
    @Column(name = "SIM_ICCID")
    private String simICCID;
    
    @Column(name = "Power_Intruption")
    private Boolean powerIntruption;
    @Column(name = "Signal_Strength")
    private Long signalStrength;
    @Column(name = "Signal_Strength_Indicator")
    private String signalStrengthIndicator;
    
    @Column(name = "Antena_Required")
    private Boolean antenaRequired;
    
    
    @Column(name = "GTW_Room_Id")
    private Long gatewayRoomId;
    @Column(name = "GTW_Floor_Id")
    private Long gatewayFloorId;
    @Column(name = "Cable_Length")
    private Double cableLength; 
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getEstimatedCableLength() {
        return EstimatedCableLength;
    }

    public int getGatewaysRequired() {
        return gatewaysRequired;
    }

    public int getMetersPerGateway() {
        return metersPerGateway;
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

    public Boolean getPowerIntruption() {
        return powerIntruption;
    }

    public Long getSignalStrength() {
        return signalStrength;
    }

    public String getSignalStrengthIndicator() {
        return signalStrengthIndicator;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getSimICCID() {
        return simICCID;
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

    public void setEstimatedCableLength(String EstimatedCableLength) {
        this.EstimatedCableLength = EstimatedCableLength;
    }

    public void setGatewaysRequired(int gatewaysRequired) {
        this.gatewaysRequired = gatewaysRequired;
    }

    public void setMetersPerGateway(int metersPerGateway) {
        this.metersPerGateway = metersPerGateway;
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

    public void setPowerIntruption(Boolean powerIntruption) {
        this.powerIntruption = powerIntruption;
    }

    public void setSignalStrength(Long signalStrength) {
        this.signalStrength = signalStrength;
    }

    public void setSignalStrengthIndicator(String signalStrengthIndicator) {
        this.signalStrengthIndicator = signalStrengthIndicator;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setSimICCID(String simICCID) {
        this.simICCID = simICCID;
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
    
    
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BOMGatewaysEst)) {
            return false;
        }
        BOMGatewaysEst other = (BOMGatewaysEst) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.BOMGatewaysEst[ id=" + id + " ]";
    }
    
}
