/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author au_addc
 */
public class RequestDTO {
    
    private String id;
    
    
    private String requestStatus;
    
     
    private Date createdDate;
    
    private Date modifiedDate;
    
    private Date surveyDateSchedule;
    
    
    private String requestRemarks;
    
    
    private Long activeBom;
    
    
    private Long activeSurvey;
    
    
    private String customerCompany;
    
    
    private String contactName;
    
    
    private String contactNumberPrimary;
    
    
    private String contactNumberSecondary;
    
    
    private String createdByName;
    
    
    private String createdBySignature;
    
    private String venodrName;
    private String emirateName;
    private String emirateRegionName;
    private BigDecimal premiseLatitude; 
    private BigDecimal premiseLongitude;
    private String premiseBuildingName;
    private String premisePlotNumber;
    private String premiseId;
    private String premiseType;
    private String premiseAddress;
    
    private String premiseSubStationNumber;
    private String createdIOTLead;
    private Long accountNumber;
    
    private String premiseLatitudeStr;
    private String premiseLongitudeStr;

    public RequestDTO() {
    }

    public RequestDTO(String id, String requestStatus, Date createdDate, Date modifiedDate, Date surveyDateSchedule, String requestRemarks, Long activeBom, Long activeSurvey, String customerCompany, String contactName, String contactNumberPrimary, String contactNumberSecondary, String createdByName, String createdBySignature, String venodrName, String emirateName, String emirateRegionName, BigDecimal premiseLatitude, BigDecimal premiseLongitude, String premiseBuildingName, String premisePlotNumber, String premiseId, String premiseType, String premiseAddress, String premiseSubStationNumber, String createdIOTLead, Long accountNumber) {
        this.id = id;
        this.requestStatus = requestStatus;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.surveyDateSchedule = surveyDateSchedule;
        this.requestRemarks = requestRemarks;
        this.activeBom = activeBom;
        this.activeSurvey = activeSurvey;
        this.customerCompany = customerCompany;
        this.contactName = contactName;
        this.contactNumberPrimary = contactNumberPrimary;
        this.contactNumberSecondary = contactNumberSecondary;
        this.createdByName = createdByName;
        this.createdBySignature = createdBySignature;
        this.venodrName = venodrName;
        this.emirateName = emirateName;
        this.emirateRegionName = emirateRegionName;
        this.premiseLatitude = premiseLatitude;
        this.premiseLongitude = premiseLongitude;
        this.premiseBuildingName = premiseBuildingName;
        this.premisePlotNumber = premisePlotNumber;
        this.premiseId = premiseId;
        this.premiseType = premiseType;
        this.premiseAddress = premiseAddress;
        this.premiseSubStationNumber = premiseSubStationNumber;
        this.createdIOTLead = createdIOTLead;
        this.accountNumber = accountNumber;
    }

    
    
    
    public String getId() {
        return id;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Date getSurveyDateSchedule() {
        return surveyDateSchedule;
    }

    public String getRequestRemarks() {
        return requestRemarks;
    }

    public Long getActiveBom() {
        return activeBom;
    }

    public Long getActiveSurvey() {
        return activeSurvey;
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactNumberPrimary() {
        if(contactNumberPrimary == null){
            contactNumberPrimary = "";
        }
        return contactNumberPrimary;
    }

    public String getContactNumberSecondary() {
        if(contactNumberSecondary == null){
            contactNumberSecondary = "";
        }
        return contactNumberSecondary;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public String getCreatedBySignature() {
        return createdBySignature;
    }

    public String getVenodrName() {
        return venodrName;
    }

    public String getEmirateName() {
        return emirateName;
    }

    public String getEmirateRegionName() {
        return emirateRegionName;
    }

    public BigDecimal getPremiseLatitude() {
        return premiseLatitude;
    }

    public BigDecimal getPremiseLongitude() {
        return premiseLongitude;
    }

    public String getPremiseBuildingName() {
        return premiseBuildingName;
    }

    public String getPremisePlotNumber() {
        return premisePlotNumber;
    }
    
    public String getPremiseId() {
        return premiseId;
    }

    public String getPremiseType() {
        return premiseType;
    }

    public String getPremiseAddress() {
        return premiseAddress;
    }

    public String getPremiseSubStationNumber() {
        return premiseSubStationNumber;
    }

    public String getCreatedIOTLead() {
        return createdIOTLead;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getPremiseLatitudeStr() {
        premiseLatitudeStr = premiseLatitude.toString();
        return premiseLatitudeStr;
    }

    public String getPremiseLongitudeStr() {
        premiseLongitudeStr = premiseLongitude.toString();
        return premiseLongitudeStr;
    }
    
    
    
    /**
     * 
     * @param id 
     */
    
    public void setId(String id) {
        this.id = id;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setSurveyDateSchedule(Date surveyDateSchedule) {
        this.surveyDateSchedule = surveyDateSchedule;
    }

    public void setRequestRemarks(String requestRemarks) {
        this.requestRemarks = requestRemarks;
    }

    public void setActiveBom(Long activeBom) {
        this.activeBom = activeBom;
    }

    public void setActiveSurvey(Long activeSurvey) {
        this.activeSurvey = activeSurvey;
    }

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactNumberPrimary(String contactNumberPrimary) {
        this.contactNumberPrimary = contactNumberPrimary;
    }

    public void setContactNumberSecondary(String contactNumberSecondary) {
        this.contactNumberSecondary = contactNumberSecondary;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public void setCreatedBySignature(String createdBySignature) {
        this.createdBySignature = createdBySignature;
    }

    public void setVenodrName(String venodrName) {
        this.venodrName = venodrName;
    }

    public void setEmirateName(String emirateName) {
        this.emirateName = emirateName;
    }

    public void setEmirateRegionName(String emirateRegionName) {
        this.emirateRegionName = emirateRegionName;
    }

    public void setPremiseLatitude(BigDecimal premiseLatitude) {
        this.premiseLatitude = premiseLatitude;
    }

    public void setPremiseLongitude(BigDecimal premiseLongitude) {
        this.premiseLongitude = premiseLongitude;
    }

    public void setPremiseBuildingName(String premiseBuildingName) {
        this.premiseBuildingName = premiseBuildingName;
    }

    public void setPremisePlotNumber(String premisePlotNumber) {
        this.premisePlotNumber = premisePlotNumber;
    }
    
    

    public void setPremiseId(String premiseId) {
        this.premiseId = premiseId;
    }

    public void setPremiseType(String premiseType) {
        this.premiseType = premiseType;
    }

    public void setPremiseAddress(String premiseAddress) {
        this.premiseAddress = premiseAddress;
    }

    public void setPremiseSubStationNumber(String premiseSubStationNumber) {
        this.premiseSubStationNumber = premiseSubStationNumber;
    }

    public void setCreatedIOTLead(String createdIOTLead) {
        this.createdIOTLead = createdIOTLead;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setPremiseLatitudeStr(String premiseLatitudeStr) {
        this.premiseLatitudeStr = premiseLatitudeStr;
    }

    public void setPremiseLongitudeStr(String premiseLongitudeStr) {
        this.premiseLongitudeStr = premiseLongitudeStr;
    }
    
    
    
}
