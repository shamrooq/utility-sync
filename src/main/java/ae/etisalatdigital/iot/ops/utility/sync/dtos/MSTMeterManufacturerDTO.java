/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTMeterModel;

/**
 *
 * @author appadmin
 */
public class MSTMeterManufacturerDTO {
    
    private Long id;
    private Long utilityId;
    private String manufacturerCode;
    private String manufacturerName;
    private String manufacturerDescription;
    
    

    public MSTMeterManufacturerDTO(Long id, Long utilityId, String manufacturerCode, String manufacturerName, String manufacturerDescription) {
        this.id = id;
        this.manufacturerCode = manufacturerCode;
        this.manufacturerName = manufacturerName;
        this.manufacturerDescription = manufacturerDescription;
        this.utilityId = utilityId;
    }

    public Long getId() {
        return id;
    }

    public String getManufacturerCode() {
        return manufacturerCode;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getManufacturerDescription() {
        return manufacturerDescription;
    }

    public Long getUtilityId() {
        return utilityId;
    }
    
    
    

    public void setId(Long id) {
        this.id = id;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setManufacturerDescription(String manufacturerDescription) {
        this.manufacturerDescription = manufacturerDescription;
    }

    public void setUtilityId(Long utilityId) {
        this.utilityId = utilityId;
    }
    
    
    
}
