/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author au_mobility
 */
public class MSTBusinessDTO {
    
    private Long id;
    
    
    private String businessCode;
    
    
    private String businessAppCode;
    
    private String businessTitle;
    
    
    private String businessDescription;
    
     
    private Date createdDate;
    
    private Date modifiedDate;
    
    
    private Boolean isDisabled;
    
    public MSTBusinessDTO(){}

    public MSTBusinessDTO(Long id, String businessCode, String businessAppCode, String businessTitle, String businessDescription, Date createdDate, Date modifiedDate, Boolean isDisabled) {
        this.id = id;
        this.businessCode = businessCode;
        this.businessAppCode = businessAppCode;
        this.businessTitle = businessTitle;
        this.businessDescription = businessDescription;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isDisabled = isDisabled;
    }

    

    public Long getId() {
        return id;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public String getBusinessAppCode() {
        return businessAppCode;
    }

    public String getBusinessTitle() {
        return businessTitle;
    }

    public String getBusinessDescription() {
        return businessDescription;
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

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public void setBusinessAppCode(String businessAppCode) {
        this.businessAppCode = businessAppCode;
    }

    public void setBusinessTitle(String businessTitle) {
        this.businessTitle = businessTitle;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
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
    
    /**
     * 
     */
    
    
    
}
