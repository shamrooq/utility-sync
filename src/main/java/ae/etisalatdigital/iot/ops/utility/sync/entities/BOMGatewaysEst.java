/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.OneToOne;

/**
 *
 * @author appadmin
 */
@Entity
@Table(name = "BOM_Gateways_Est")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BOMGatewaysEst.findAll", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO(m.id,m.bom.id,m.gatewaysType,m.gatewayModel,m.gatewaysRequired,m.metersPerGateway,m.EstimatedCableLength, m.gatewaysVendor, m.gatewaysLocation, m.gatewaysDaisychain, m.gatewaysChainLabel, m.serialNumber, m.simDetails.simICCID, m.powerIntruption, m.signalStrength, m.signalStrengthIndicator, m.antenaRequired, m.mstRoom.id, m.mstFloor.id, m.cableLength ) FROM BOMGatewaysEst m")
        ,@NamedQuery(name = "BOMGatewaysEst.findAllByBOMID", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO(m.id,m.bom.id,m.gatewaysType,m.gatewayModel.gatewayTypeCode,m.gatewaysRequired,m.metersPerGateway,m.EstimatedCableLength, m.gatewaysVendor, m.gatewaysLocation, m.gatewaysDaisychain, m.gatewaysChainLabel, m.serialNumber, m.simDetails.simICCID, m.powerIntruption, m.signalStrength, m.signalStrengthIndicator, m.antenaRequired, m.mstRoom.id, m.mstRoom.id, m.cableLength ) FROM BOMGatewaysEst m where m.bom.id = :bomId")
        , @NamedQuery(name = "BOMGatewaysEst.DELETE", query = "DELETE FROM BOMGatewaysEst m WHERE m.id = :id")
        ,@NamedQuery(name = "BOMGatewaysEst.findSomeByBomId", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO(m.id,m.bom.id,m.gatewaysType,m.gatewayModel,m.gatewaysRequired,m.metersPerGateway,m.EstimatedCableLength, m.gatewaysVendor, m.gatewaysLocation, m.gatewaysDaisychain, m.gatewaysChainLabel,m.serialNumber,m.simDetails,m.mstFloor.floorCode,m.mstRoom.roomCode) FROM BOMGatewaysEst m where m.bom.id = :bomId")
 })
public class BOMGatewaysEst implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EST_GTW_ID")
    private BigInteger id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BOM_ID")
    private Boms bom;
    
    @Column(name = "EST_BOM_GTW_Type")
    private String gatewaysType;
    //@Column(name = "EST_GTW_Type")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EST_GTW_Type")
    private MSTGatewayTypes gatewayModel;
    
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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sim_id")
    private SimDetails simDetails;
    @Column(name = "Power_Intruption")
    private Boolean powerIntruption;
    @Column(name = "Signal_Strength")
    private Long signalStrength;
    @Column(name = "Signal_Strength_Indicator")
    private String signalStrengthIndicator;
    
    @Column(name = "Antena_Required")
    private Boolean antenaRequired;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GTW_Room_Id")
    private MSTRoom mstRoom;
            
    //private Long gatewayRoomId;

//    @Column(name = "GTW_Floor_Id")
//    private Long gatewayFloorId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GTW_Floor_Id")
    private MSTFloor mstFloor;
            
    @Column(name = "Cable_Length")
    private Double cableLength;
    @Column(name="Gtw_Floor")
    private String gatewayFloor;
    @Column(name="Gtw_Room")
    private String gatewayRoom;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "meterGateway")
    private List<BOMMeters> bomMeterList;
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public BigInteger getId() {
        return id;
    }

    public String getGatewaysType() {
        return gatewaysType;
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

    public Boolean getAntenaRequired() {
        return antenaRequired;
    }

    public Double getCableLength() {
        return cableLength;
    }
    
    
    
    
    
    /**
     * 
     * @param id 
     */
    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setGatewaysType(String gatewaysType) {
        this.gatewaysType = gatewaysType;
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

    public void setAntenaRequired(Boolean antenaRequired) {
        this.antenaRequired = antenaRequired;
    }

    public void setCableLength(Double cableLength) {
        this.cableLength = cableLength;
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

    public MSTRoom getMstRoom() {
        return mstRoom;
    }

    public void setMstRoom(MSTRoom mstRoom) {
        this.mstRoom = mstRoom;
    }

    public MSTFloor getMstFloor() {
        return mstFloor;
    }

    public void setMstFloor(MSTFloor mstFloor) {
        this.mstFloor = mstFloor;
    }

    public MSTGatewayTypes getGatewayModel() {
        return gatewayModel;
    }

    public void setGatewayModel(MSTGatewayTypes gatewayModel) {
        this.gatewayModel = gatewayModel;
    }

    public SimDetails getSimDetails() {
        return simDetails;
    }

    public void setSimDetails(SimDetails simDetails) {
        this.simDetails = simDetails;
    }

    public List<BOMMeters> getBomMeterList() {
        return bomMeterList;
    }

    public void setBomMeterList(List<BOMMeters> bomMeterList) {
        this.bomMeterList = bomMeterList;
    }

    public Boms getBom() {
        return bom;
    }

    public void setBom(Boms bom) {
        this.bom = bom;
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
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.BOMGatewaysEst[ id=" + id + " ]";
    }
    
}
