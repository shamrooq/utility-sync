/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author appadmin
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EquipmentRequestModel {
    @JsonProperty("utility_id")
    private Long utilityId;
    @JsonProperty("type_id")
    private Long typeId;
    @JsonProperty("model_id")
    private Long modelId;
    private String code;
    private String serialNumber;
    @JsonProperty("parent_code")
    private String parentCode;
    private Long protocolId;
    private String measuringPoint;
    private Long accountNumber;
    private List<Property> properties;
    public Long getUtilityId() {
        return utilityId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public Long getModelId() {
        return modelId;
    }

    public String getCode() {
        return code;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getParentCode() {
        return parentCode;
    }
    
    /**
     * 
     * @param utility_id 
     */
    public void setUtilityId(Long utility_id) {
        this.utilityId = utility_id;
    }

    public void setTypeId(Long type_id) {
        this.typeId = type_id;
    }

    public void setModelId(Long model_id) {
        this.modelId = model_id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setParentCode(String parent_code) {
        this.parentCode = parent_code;
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
