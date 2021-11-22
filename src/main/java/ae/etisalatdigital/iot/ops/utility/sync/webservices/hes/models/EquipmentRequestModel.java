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
    private Integer utilityId;
    @JsonProperty("type_id")
    private Integer typeId;
    @JsonProperty("model_id")
    private Integer modelId;
    private String code;
    private String serialNumber;
    @JsonProperty("parent_code")
    private String parentCode;
    private Integer protocolId;
    private String measuringPoint;
    private Long accountNumber;
    private List<Property> properties;
    public Integer getUtilityId() {
        return utilityId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public Integer getModelId() {
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
    public void setUtilityId(Integer utility_id) {
        this.utilityId = utility_id;
    }

    public void setTypeId(Integer type_id) {
        this.typeId = type_id;
    }

    public void setModelId(Integer model_id) {
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

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
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
