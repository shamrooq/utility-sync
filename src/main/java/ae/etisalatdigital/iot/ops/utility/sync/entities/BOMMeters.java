/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author au_mobility
 */
@Entity
@Table(name = "BOM_Meter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BOMMeters.findAll", query = "SELECT m FROM BOMMeters m")
        ,@NamedQuery(name = "BOMMeters.findAllByBOMID", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO(m.id,m.bomId,m.meterStatus,m.meterType,m.meterCorrelationID,m.meterType,m.meterSerial,m.meterLabelGTW,m.meterLabelCBL,m.meterLabelJBX,m.modifiedDate,m.meterAmi, m.meterManufacturerId, m.meterModelId, m.meterProtocolId, m.meterRoomId, m.meterFloorId, m.meterManufacturerModel ) FROM BOMMeters m where m.bomId = :bomId and m.meterStatus <> 'DELETED'")
        ,@NamedQuery(name = "BOMMeters.findAllByBomIdAndBomMetertype", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO(m.id,m.bomId,m.meterStatus,m.meterType,m.meterCorrelationID,m.meterType,m.meterSerial,m.meterLabelGTW,m.meterLabelCBL,m.meterLabelJBX,m.modifiedDate,m.meterAmi, m.meterManufacturerId, m.meterModelId, m.meterProtocolId, m.meterRoomId, m.meterFloorId, m.meterManufacturerModel,m.meterGtwId ) FROM BOMMeters m where m.bomId = :bomId and m.bomMeterType = :bomMeterType and m.meterStatus <> 'DELETED'")
        , @NamedQuery(name = "BOMMeters.DELETE", query = "DELETE FROM BOMMeters m WHERE m.id = :id")
 })
public class BOMMeters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOM_METER_ID")
    private Long id;
    
    @Column(name = "BOM_ID")
    private Long bomId;
    
    
    @Column(name = "METER_Status")
    private String meterStatus;
    @Column(name = "BOM_METER_Type")
    private String bomMeterType;
    @Column(name = "Meter_Correlation_ID")
    private String meterCorrelationID;
    @Column(name = "METER_Floor")
    private String meterFloor;
    @Column(name = "METER_Room")
    private String meterRoom;
    @Column(name = "METER_Manufacturer")
    private String meterManufacturer;
    @Column(name = "METER_Model")
    private String meterModel;
    @Column(name = "METER_Type")
    private String meterType;
    
    @Column(name = "METER_Serial")
    private String meterSerial;
    
    @Column(name = "METER_GTW_Label")
    private String meterLabelGTW;
    @Column(name = "METER_CBL_Label")
    private String meterLabelCBL;
    @Column(name = "METER_JBX_Label")
    private String meterLabelJBX;
    
    
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @Column(name = "METER_AMI_NONAMI")
    private String meterAmi;
    @Column(name = "METER_Manufacturer_Id")
    private Long meterManufacturerId;
    @Column(name = "METER_Model_Id")
    private Long meterModelId;
    @Column(name = "METER_Protocol_Id")
    private Long meterProtocolId;
    @Column(name = "METER_Room_Id")
    private Long meterRoomId;
    @Column(name = "METER_Floor_Id")
    private Long meterFloorId;

    @JoinColumn(name = "METER_Manufacturer_Id", referencedColumnName = "MANUFACTURER_ID", insertable = false, updatable = false)
    @ManyToOne
    private MSTMeterManufacturer meterManufacturerModel;

    @Column(name = "METER_GTW_ID")
    private BigInteger meterGtwId;

    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getBomId() {
        return bomId;
    }

    public String getMeterStatus() {
        return meterStatus;
    }

    public String getBomMeterType() {
        return bomMeterType;
    }

    public String getMeterCorrelationID() {
        return meterCorrelationID;
    }

    public String getMeterFloor() {
        return meterFloor;
    }

    public String getMeterRoom() {
        return meterRoom;
    }

    public String getMeterManufacturer() {
        return meterManufacturer;
    }

    public String getMeterModel() {
        return meterModel;
    }

    public String getMeterType() {
        return meterType;
    }

    public String getMeterSerial() {
        return meterSerial;
    }

    public String getMeterLabelGTW() {
        return meterLabelGTW;
    }

    public String getMeterLabelCBL() {
        return meterLabelCBL;
    }

    public String getMeterLabelJBX() {
        return meterLabelJBX;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public String getMeterAmi() {
        return meterAmi;
    }

    public Long getMeterManufacturerId() {
        return meterManufacturerId;
    }

    public Long getMeterModelId() {
        return meterModelId;
    }

    public Long getMeterProtocolId() {
        return meterProtocolId;
    }

    public Long getMeterRoomId() {
        return meterRoomId;
    }

    public Long getMeterFloorId() {
        return meterFloorId;
    }

    public MSTMeterManufacturer getMeterManufacturerModel() {
        return meterManufacturerModel;
    }

    
    
    
    
    /*
    *
    */

    public void setId(Long id) {
        this.id = id;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public void setMeterStatus(String meterStatus) {
        this.meterStatus = meterStatus;
    }

    public void setBomMeterType(String bomMeterType) {
        this.bomMeterType = bomMeterType;
    }

    public void setMeterCorrelationID(String meterCorrelationID) {
        this.meterCorrelationID = meterCorrelationID;
    }

    public void setMeterFloor(String meterFloor) {
        this.meterFloor = meterFloor;
    }

    public void setMeterRoom(String meterRoom) {
        this.meterRoom = meterRoom;
    }

    public void setMeterManufacturer(String meterManufacturer) {
        this.meterManufacturer = meterManufacturer;
    }

    public void setMeterModel(String meterModel) {
        this.meterModel = meterModel;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public void setMeterSerial(String meterSerial) {
        this.meterSerial = meterSerial;
    }

    public void setMeterLabelGTW(String meterLabelGTW) {
        this.meterLabelGTW = meterLabelGTW;
    }

    public void setMeterLabelCBL(String meterLabelCBL) {
        this.meterLabelCBL = meterLabelCBL;
    }

    public void setMeterLabelJBX(String meterLabelJBX) {
        this.meterLabelJBX = meterLabelJBX;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setMeterAmi(String meterAmi) {
        this.meterAmi = meterAmi;
    }

    public void setMeterManufacturerId(Long meterManufacturerId) {
        this.meterManufacturerId = meterManufacturerId;
    }

    public void setMeterModelId(Long meterModelId) {
        this.meterModelId = meterModelId;
    }

    public void setMeterProtocolId(Long meterProtocolId) {
        this.meterProtocolId = meterProtocolId;
    }

    public void setMeterRoomId(Long meterRoomId) {
        this.meterRoomId = meterRoomId;
    }

    public void setMeterFloorId(Long meterFloorId) {
        this.meterFloorId = meterFloorId;
    }

    public void setMeterManufacturerModel(MSTMeterManufacturer meterManufacturerModel) {
        this.meterManufacturerModel = meterManufacturerModel;
    }

    public BigInteger getMeterGtwId() {
        return meterGtwId;
    }

    public void setMeterGtwId(BigInteger meterGtwId) {
        this.meterGtwId = meterGtwId;
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
        if (!(object instanceof BOMMeters)) {
            return false;
        }
        BOMMeters other = (BOMMeters) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.BOMMeters[ id=" + id + " ]";
    }
    
}
