/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import java.util.Date;


/**
 *
 * @author au_addc
 */
public class BOMDTO {
    
    private Long id;
    
    private String utilityNumber;
    
    
    private String bomStatus;
    
    
    private String bomRemarks;
    
    
    private String surveyorName;
    
    
    private String surveyorSignature;
    
    private Date createdDate;
    
    private Date modifiedDate;
    
    private Date surveyDate;

    
    public BOMDTO() {
    }

    public BOMDTO(Long id, String utilityNumber, String bomStatus, String bomRemarks, String surveyorName, String surveyorSignature, Date createdDate, Date modifiedDate, Date surveyDate) {
        this.id = id;
        this.utilityNumber = utilityNumber;
        this.bomStatus = bomStatus;
        this.bomRemarks = bomRemarks;
        this.surveyorName = surveyorName;
        this.surveyorSignature = surveyorSignature;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.surveyDate = surveyDate;
    }

    public Long getId() {
        return id;
    }

    public String getUtilityNumber() {
        return utilityNumber;
    }

    public String getBomStatus() {
        return bomStatus;
    }

    public String getBomRemarks() {
        return bomRemarks;
    }

    public String getSurveyorName() {
        return surveyorName;
    }

    public String getSurveyorSignature() {
        return surveyorSignature;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }
    
    
    /**
     * 
     * @param id 
     */
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setUtilityNumber(String utilityNumber) {
        this.utilityNumber = utilityNumber;
    }

    public void setBomStatus(String bomStatus) {
        this.bomStatus = bomStatus;
    }

    public void setBomRemarks(String bomRemarks) {
        this.bomRemarks = bomRemarks;
    }

    public void setSurveyorName(String surveyorName) {
        this.surveyorName = surveyorName;
    }

    public void setSurveyorSignature(String surveyorSignature) {
        this.surveyorSignature = surveyorSignature;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }
    
    
    
    
}
