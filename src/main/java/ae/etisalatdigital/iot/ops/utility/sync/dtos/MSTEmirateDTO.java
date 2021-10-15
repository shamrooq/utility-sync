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
public class MSTEmirateDTO {
    
    private Long id;
    
    
    private String emirateCode;
    
    
    private String emirateName;
    
    
    private String emirateDescription;
    
     
    private Date createdDate;
    
    private Date modifiedDate;
    
    
    private Boolean isDisabled;
    
    public MSTEmirateDTO(){}

    public MSTEmirateDTO(Long id, String emirateCode, String emirateName, String emirateDescription, Date createdDate, Date modifiedDate, Boolean isDisabled) {
        this.id = id;
        this.emirateCode = emirateCode;
        this.emirateName = emirateName;
        this.emirateDescription = emirateDescription;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isDisabled = isDisabled;
    }

    public Long getId() {
        return id;
    }

    public String getEmirateCode() {
        return emirateCode;
    }

    public String getEmirateName() {
        return emirateName;
    }

    public String getEmirateDescription() {
        return emirateDescription;
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

    public void setEmirateCode(String emirateCode) {
        this.emirateCode = emirateCode;
    }

    public void setEmirateName(String emirateName) {
        this.emirateName = emirateName;
    }

    public void setEmirateDescription(String emirateDescription) {
        this.emirateDescription = emirateDescription;
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
