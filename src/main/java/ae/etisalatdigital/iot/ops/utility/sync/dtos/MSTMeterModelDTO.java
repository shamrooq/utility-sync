/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

/**
 *
 * @author appadmin
 */
public class MSTMeterModelDTO {
    
    private Long id;
    private String modelCode;
    private String modelTitle;
    private String modelDescription;
    
    private Long manufacturerId;
    
    public MSTMeterModelDTO() {
    }

    public MSTMeterModelDTO(Long id, Long manufacturerId, String modelCode, String modelTitle, String modelDescription) {
        this.id = id;
        this.modelCode = modelCode;
        this.modelTitle = modelTitle;
        this.modelDescription = modelDescription;
        this.manufacturerId = manufacturerId;
    }

    public Long getId() {
        return id;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }
    
    
    public String getModelCode() {
        return modelCode;
    }

    public String getModelTitle() {
        return modelTitle;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public void setModelTitle(String modelTitle) {
        this.modelTitle = modelTitle;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
    
    
}
