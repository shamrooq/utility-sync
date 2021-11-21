/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models;

/**
 *
 * @author appadmin
 */
public class EquipmentRequestModel {
    private Integer utility_id;
    private Integer type_id;
    private Integer model_id;
    private Integer protocolId;
    
    private String code;
    private String serialNumber;
    private String parent_code;
    private String measuringPoint;

    public Integer getUtility_id() {
        return utility_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public Integer getModel_id() {
        return model_id;
    }

    public String getCode() {
        return code;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getParent_code() {
        return parent_code;
    }

    public Integer getProtocolId() {
        return protocolId;
    }

    public String getMeasuringPoint() {
        return measuringPoint;
    }
    
    
    
    /**
     * 
     * @param utility_id 
     */
    public void setUtility_id(Integer utility_id) {
        this.utility_id = utility_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public void setModel_id(Integer model_id) {
        this.model_id = model_id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public void setMeasuringPoint(String measuringPoint) {
        this.measuringPoint = measuringPoint;
    }
    
    
    
}
