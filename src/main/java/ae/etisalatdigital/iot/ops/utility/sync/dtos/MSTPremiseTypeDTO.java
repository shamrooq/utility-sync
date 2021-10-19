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
public class MSTPremiseTypeDTO {
    
    
    private Long id;
    
   
    private String premiseTypeCode;
    
    private String premiseTypeName;
    
    private String premiseTypeDescription;
    
    
    private Date createdDate;
    
    private Date modifiedDate;
    
    private Boolean isDisabled;
    
    public MSTPremiseTypeDTO()
    {}
    
    public MSTPremiseTypeDTO(Long id, String premiseTypeCode, String premiseTypeName, String premiseTypeDescription, Date createdDate, Date modifiedDate, Boolean isDisabled) {
        this.id = id;
        this.premiseTypeCode = premiseTypeCode;
        this.premiseTypeName = premiseTypeName;
        this.premiseTypeDescription = premiseTypeDescription;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isDisabled = isDisabled;
    }

    public Long getId() {
        return id;
    }

    public String getPremiseTypeCode() {
        return premiseTypeCode;
    }

    public String getPremiseTypeName() {
        return premiseTypeName;
    }

    public String getPremiseTypeDescription() {
        return premiseTypeDescription;
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
    
    /**
     * 
     * @param id
     */

    public void setId(Long id) {
        this.id = id;
    }

    public void setPremiseTypeCode(String premiseTypeCode) {
        this.premiseTypeCode = premiseTypeCode;
    }

    public void setPremiseTypeName(String premiseTypeName) {
        this.premiseTypeName = premiseTypeName;
    }

    public void setPremiseTypeDescription(String premiseTypeDescription) {
        this.premiseTypeDescription = premiseTypeDescription;
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
