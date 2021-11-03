/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author appadmin
 */
public class EquipmentRequestModel {
    private Long utility_id;
    private Long type_id;
    private Long model_id;
    private String code;
    private String serialNumber;
    private String parent_code;
    private Long protocolId;
    private String measuringPoint;
    private Long accountNumber;
    private List<Property> properties;
    public Long getUtility_id() {
        return utility_id;
    }

    public Long getType_id() {
        return type_id;
    }

    public Long getModel_id() {
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
    
    /**
     * 
     * @param utility_id 
     */
    public void setUtility_id(Long utility_id) {
        this.utility_id = utility_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public void setModel_id(Long model_id) {
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

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public String getMeasuringPoint() {
        return measuringPoint;
    }

    public void setMeasuringPoint(String measuringPoint) {
        this.measuringPoint = measuringPoint;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Property> getProperties() {
        if(this.properties == null){
            this.properties = new ArrayList<>();
        }
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

}
