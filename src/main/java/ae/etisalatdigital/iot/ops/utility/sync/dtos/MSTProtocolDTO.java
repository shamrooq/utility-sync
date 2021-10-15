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
public class MSTProtocolDTO {
    
    private Long id;
    
    
    private String protocolCode;
    
    
    private String protocolTitle;
    
    private String protocolDescription;
    
    
    
    
     
    private Date createdDate;
    
    private Date modifiedDate;
    
    
    private Boolean isDisabled;
    
    public MSTProtocolDTO(){}

    public MSTProtocolDTO(Long id, String protocolCode, String protocolTitle, String protocolDescription, Date createdDate, Date modifiedDate, Boolean isDisabled) {
        this.id = id;
        this.protocolCode = protocolCode;
        this.protocolTitle = protocolTitle;
        this.protocolDescription = protocolDescription;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isDisabled = isDisabled;
    }

    

    

    public Long getId() {
        return id;
    }

    public String getProtocolCode() {
        return protocolCode;
    }

    public String getProtocolTitle() {
        return protocolTitle;
    }

    public String getProtocolDescription() {
        return protocolDescription;
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

    public void setProtocolCode(String protocolCode) {
        this.protocolCode = protocolCode;
    }

    public void setProtocolTitle(String protocolTitle) {
        this.protocolTitle = protocolTitle;
    }

    public void setProtocolDescription(String protocolDescription) {
        this.protocolDescription = protocolDescription;
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
