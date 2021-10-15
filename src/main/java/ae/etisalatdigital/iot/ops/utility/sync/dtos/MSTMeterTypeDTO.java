/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;


/**
 *
 * @author au_addc
 */
public class MSTMeterTypeDTO {
    
    
    private Long id;
    
   
    private String meterTypeCode;
    
    private String meterTypeName;
    
    private String meterTypeDescription;
    
    
    
    
    public MSTMeterTypeDTO()
    {}
    
    
    public Long getId() {
        return id;
    }

    public String getMeterTypeCode() {
        return meterTypeCode;
    }

    public String getMeterTypeName() {
        return meterTypeName;
    }

    public String getMeterTypeDescription() {
        return meterTypeDescription;
    }

    

    
    
    /**
     * 
     * @param id
     */

    public void setId(Long id) {
        this.id = id;
    }

    public void setMeterTypeCode(String meterTypeCode) {
        this.meterTypeCode = meterTypeCode;
    }

    public void setMeterTypeName(String meterTypeName) {
        this.meterTypeName = meterTypeName;
    }

    public void setMeterTypeDescription(String meterTypeDescription) {
        this.meterTypeDescription = meterTypeDescription;
    }

    
    
    
    
    
    
}
