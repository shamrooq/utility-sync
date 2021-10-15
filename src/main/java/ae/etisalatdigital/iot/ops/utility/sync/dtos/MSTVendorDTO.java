/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import java.util.Date;


/**
 *
 * @author au_mobility
 */
public class MSTVendorDTO {
    
    
    
    private Long id;
    
    
    private String vendorCode;
    
    private String vendorTitle;
    
    private String vendorDescription;
    
    private Date createdDate;
   
    private Date modifiedDate;
    
    private Boolean isDisabled;
    
    public MSTVendorDTO(){}

    public MSTVendorDTO(Long id, String vendorCode, String vendorTitle, String vendorDescription, Date createdDate, Date modifiedDate, Boolean isDisabled) {
        this.id = id;
        this.vendorCode = vendorCode;
        this.vendorTitle = vendorTitle;
        this.vendorDescription = vendorDescription;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isDisabled = isDisabled;
    }

    public Long getId() {
        return id;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public String getVendorTitle() {
        return vendorTitle;
    }

    public String getVendorDescription() {
        return vendorDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public void setVendorTitle(String vendorTitle) {
        this.vendorTitle = vendorTitle;
    }

    public void setVendorDescription(String vendorDescription) {
        this.vendorDescription = vendorDescription;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
    }
    
    
}
