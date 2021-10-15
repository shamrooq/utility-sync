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
public class MSTEmirateRegionDTO {
    
    private Long id;
    
    
    private String emirateRegionCode;
    
    
    private String emirateRegionName;
    
    
    private String emirateRegionDescription;
    
     
    private Date createdDate;
    
    private Date modifiedDate;
    
    
    private Boolean isDisabled;
    
    public MSTEmirateRegionDTO(){}

    public MSTEmirateRegionDTO(Long id, String emirateRegionCode, String emirateRegionName, String emirateRegionDescription, Date createdDate, Date modifiedDate, Boolean isDisabled) {
        this.id = id;
        this.emirateRegionCode = emirateRegionCode;
        this.emirateRegionName = emirateRegionName;
        this.emirateRegionDescription = emirateRegionDescription;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isDisabled = isDisabled;
    }

    
    
    
    public Long getId() {
        return id;
    }

    public String getEmirateRegionCode() {
        return emirateRegionCode;
    }

    public String getEmirateRegionName() {
        return emirateRegionName;
    }

    public String getEmirateRegionDescription() {
        return emirateRegionDescription;
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

    public void setEmirateRegionCode(String emirateRegionCode) {
        this.emirateRegionCode = emirateRegionCode;
    }

    public void setEmirateRegionName(String emirateRegionName) {
        this.emirateRegionName = emirateRegionName;
    }

    public void setEmirateRegionDescription(String emirateRegionDescription) {
        this.emirateRegionDescription = emirateRegionDescription;
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
