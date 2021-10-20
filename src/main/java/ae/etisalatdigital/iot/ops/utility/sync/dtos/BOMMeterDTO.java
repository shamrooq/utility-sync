/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTFloor;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTMeterManufacturer;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTMeterModel;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTRoom;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;


/**
 *
 * @author au_mobility
 */
public class BOMMeterDTO implements Comparable<BOMMeterDTO>{

    private Long id;
    
    private Long bomId;
    
    private String meterStatus;
    
    private String bomMeterType;
    
    private String meterCorrelationID;
    
    private String meterFloor;
    
    private String meterRoom;
    
    private String meterManufacturer;
    
    private String meterModel;
    
    private String meterType;
    
    private String meterSerial;
    
    private String meterLabelGTW;
    private String meterLabelCBL;
    private String meterLabelJBX;
    
    private Date modifiedDate;
    
    private Long meterRoomId;
    private Long meterFloorId;
    
    private String meterAmi;
    private Long meterManufacturerId;
    private Long meterModelId;
    private Long meterProtocolId;
    private MSTMeterManufacturer meterManufacturerModel;

    private BigInteger meterGtwId;

    public BOMMeterDTO(){}

    
    public BOMMeterDTO(Long id, Long bomId, String meterStatus, String bomMeterType, String meterCorrelationID, String meterFloor, String meterRoom, String meterManufacturer, String meterModel, String meterType, String meterSerial) {
        this.id = id;
        this.bomId = bomId;
        this.meterStatus = meterStatus;
        this.bomMeterType = bomMeterType;
        this.meterCorrelationID = meterCorrelationID;
        this.meterFloor = meterFloor;
        this.meterRoom = meterRoom;
        this.meterManufacturer = meterManufacturer;
        this.meterModel = meterModel;
        this.meterType = meterType;
        this.meterSerial = meterSerial;
    }

    public BOMMeterDTO(Long id, Long bomId, String meterStatus, String bomMeterType, String meterCorrelationID, String meterFloor, String meterRoom, String meterManufacturer, String meterModel, String meterType, String meterSerial, String meterLabelGTW, String meterLabelCBL, String meterLabelJBX) {
        this.id = id;
        this.bomId = bomId;
        this.meterStatus = meterStatus;
        this.bomMeterType = bomMeterType;
        this.meterCorrelationID = meterCorrelationID;
        this.meterFloor = meterFloor;
        this.meterRoom = meterRoom;
        this.meterManufacturer = meterManufacturer;
        this.meterModel = meterModel;
        this.meterType = meterType;
        this.meterSerial = meterSerial;
        this.meterLabelGTW = meterLabelGTW;
        this.meterLabelCBL = meterLabelCBL;
        this.meterLabelJBX = meterLabelJBX;
    }

    public BOMMeterDTO(Long id, Long bomId, String meterStatus, String bomMeterType, String meterCorrelationID, String meterType, String meterSerial, String meterLabelGTW, String meterLabelCBL, String meterLabelJBX, Date modifiedDate,
                       String meterAmi, Long meterManufacturerId, Long meterModelId, Long meterProtocolId, Long meterRoomId, Long meterFloorId, MSTMeterManufacturer meterManufacturerModel) {
        this.id = id;
        this.bomId = bomId;
        this.meterStatus = meterStatus;
        this.bomMeterType = bomMeterType;
        this.meterCorrelationID = meterCorrelationID;
        
        this.meterType = meterType;
        this.meterSerial = meterSerial;
        this.meterLabelGTW = meterLabelGTW;
        this.meterLabelCBL = meterLabelCBL;
        this.meterLabelJBX = meterLabelJBX;
        this.modifiedDate = modifiedDate;
        
        this.meterAmi = meterAmi;
        this.meterManufacturerId = meterManufacturerId;
        this.meterModelId = meterModelId;
        this.meterProtocolId = meterProtocolId;
        this.meterRoomId = meterRoomId;
        this.meterFloorId = meterFloorId;
        this.meterManufacturerModel = meterManufacturerModel;
    }

    public BOMMeterDTO(Long id, Long bomId, String meterStatus, String meterType, String meterCorrelationID,
                       String bomMeterType, String meterSerial, String meterLabelGTW, String meterLabelCBL, String meterLabelJBX,
                       Date modifiedDate, String meterAmi, Long meterProtocolId,
                       MSTRoom mstRoom, MSTFloor mstFloor, MSTMeterManufacturer meterManufacturerModel,
                       MSTMeterModel mstMeterModel,BigInteger meterGtwId) {
        this.id = id;
        this.bomId = bomId;
        this.meterStatus = meterStatus;
        this.bomMeterType = bomMeterType;
        this.meterCorrelationID = meterCorrelationID;
        this.meterType = meterType;
        this.meterSerial = meterSerial;
        this.meterLabelGTW = meterLabelGTW;
        this.meterLabelCBL = meterLabelCBL;
        this.meterLabelJBX = meterLabelJBX;
        this.modifiedDate = modifiedDate;
        this.meterAmi = meterAmi;
        this.meterProtocolId = meterProtocolId;
        this.meterRoomId = mstRoom.getId();
        this.meterFloorId = mstFloor.getId();
        this.meterManufacturerModel = meterManufacturerModel;
        this.meterManufacturer = this.meterManufacturerModel.getManufacturerName();
        this.meterModel=mstMeterModel.getModelTitle();
        this.meterManufacturerId = meterManufacturerModel.getId();
        this.meterModelId = mstMeterModel.getId();
        this.meterGtwId = meterGtwId;
        this.meterFloor=mstFloor.getFloorCode();
        this.meterRoom=mstRoom.getRoomCode();
    }

    
    
    

    public Long getId() {
        return id;
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

    public Long getMeterRoomId() {
        return meterRoomId;
    }

    public Long getMeterFloorId() {
        return meterFloorId;
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

    public void setMeterRoomId(Long meterRoomId) {
        this.meterRoomId = meterRoomId;
    }

    public void setMeterFloorId(Long meterFloorId) {
        this.meterFloorId = meterFloorId;
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
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final BOMMeterDTO other = (BOMMeterDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(BOMMeterDTO t) {
        return this.id.compareTo(t.id);
    }
}
