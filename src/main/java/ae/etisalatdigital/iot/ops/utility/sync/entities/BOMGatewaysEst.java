/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
    @NamedQuery(name = "BOMGatewaysEst.findAll", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO(m.id,m.bomId,m.gatewaysType,m.gatewaysTypeProposed,m.gatewaysRequired,m.metersPerGateway,m.EstimatedCableLength, m.gatewaysVendor, m.gatewaysLocation, m.gatewaysDaisychain, m.gatewaysChainLabel ) FROM BOMGatewaysEst m"),
    @NamedQuery(name = "BOMGatewaysEst.findAllByBOMID", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO(m.id,m.bomId,m.gatewaysType,m.gatewaysTypeProposed,m.gatewaysRequired,m.metersPerGateway,m.EstimatedCableLength, m.gatewaysVendor, m.gatewaysLocation, m.gatewaysDaisychain, m.gatewaysChainLabel,m.serialNumber,m.simICCID,m.gatewayFloor,m.gatewayRoom) FROM BOMGatewaysEst m where m.bomId = :bomId"),
    @NamedQuery(name = "BOMGatewaysEst.DELETE", query = "DELETE FROM BOMGatewaysEst m WHERE m.id = :id")
})
public class BOMGatewaysEst implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EST_GTW_ID")
    private BigInteger id;
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
    private BigInteger simICCID;
    @Column(name="Power_Intruption")
    private Boolean powerIntruption;
    @Column(name="Signal_Strength")
    private Long signalStrength;
    @Column(name="Signal_Strength_Indicator")
    private String signalStrengthIndicator;
    @Column(name="Gtw_Floor")
    private String gatewayFloor;
    @Column(name="Gtw_Room")
    private String gatewayRoom;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigInteger getSimICCID() {
        return simICCID;
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
    public static long getSerialVersionUID() {
        return serialVersionUID;
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