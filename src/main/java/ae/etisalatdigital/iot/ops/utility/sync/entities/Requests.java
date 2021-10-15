/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author au_addc
 */
@Entity
@Table(name = "US_Request", catalog = "UTILITYSYNC", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requests.findAll", query = "SELECT m FROM Requests m")
        ,@NamedQuery(name = "Requests.findByUtilityNumber", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.RequestDTO(m.id, m.requestStatus, m.createdDate, m.modifiedDate, m.surveyDateSchedule, m.requestRemarks, m.activeBom, m.activeSurvey, m.customerCompany, m.contactName, m.contactNumberPrimary, m.contactNumberSecondary, m.createdByName, m.createdBySignature, m.venodrName, m.emirateName, m.emirateRegionName, m.premiseLatitude, m.premiseLongitude, m.premiseBuildingName, m.premisePlotNumber, m.premiseId, m.premiseType, m.premiseAddress, m.premiseSubStationNumber, m.createdIOTLead, m.accountNumber) FROM Requests m where m.id = :utilityNumber")
})
public class Requests implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UTILITY_NUMBER", nullable = false, length = 50)
    private String id;
    
    @Column(name = "REQUEST_Status", nullable = false, length = 50)
    private String requestStatus;
    
    
    
    @Column(name = "Created_Date")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date createdDate;
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "Survey_Scheduled")
    @Temporal(TemporalType.TIMESTAMP)
    private Date surveyDateSchedule;
    
    @Column(name = "Remarks", length = 400)
    private String requestRemarks;
    
    @Column(name = "Account_Number")
    private Long accountNumber;
    
    @Column(name = "Active_BOM")
    private Long activeBom;
    
    @Column(name = "Active_Survey")
    private Long activeSurvey;
    
    @Column(name = "Customer_Company", length = 200)
    private String customerCompany;
    
    @Column(name = "Contact_Name", length = 200)
    private String contactName;
    
    @Column(name = "Contact_Primary_Number", length = 20)
    private String contactNumberPrimary;
                   
    @Column(name = "Contact_Secondary_Number", length = 20)
    private String contactNumberSecondary;
    
    @Column(name = "Created_By_Name", length = 200)
    private String createdByName;
    
    @Column(name = "Created_By_Signature", length = 200)
    private String createdBySignature;
    
    @Column(name = "Vendor_Name", length = 100)
    private String venodrName;
    @Column(name = "Emirate_Name", length = 100)
    private String emirateName;
    @Column(name = "Emirate_Region_Name", length = 100)
    private String emirateRegionName;
    @Column(name = "Premise_Latitude", length = 100)
    private BigDecimal premiseLatitude; 
    @Column(name = "Premise_Longitude", length = 100)
    private BigDecimal premiseLongitude;
    @Column(name = "Premise_Building_Name", length = 200)
    private String premiseBuildingName;
    @Column(name = "Premise_ID", length = 100)
    private String premiseId;
    @Column(name = "Premise_Plot_Number", length = 200)
    private String premisePlotNumber;
    @Column(name = "Premise_Type", length = 100)
    private String premiseType;
    @Column(name = "Premise_Address", length = 400)
    private String premiseAddress;
    
    @Column(name = "Substation_Number", length = 100)
    private String premiseSubStationNumber;
     @Column(name = "Created_IOT_Lead", length = 100)
    private String createdIOTLead;
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
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
        return contactNumberPrimary;
    }

    public String getContactNumberSecondary() {
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

    public String getPremiseId() {
        return premiseId;
    }

    public String getPremisePlotNumber() {
        return premisePlotNumber;
    }

    public String getPremiseType() {
        return premiseType;
    }

    public String getPremiseAddress() {
        return premiseAddress;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getPremiseSubStationNumber() {
        return premiseSubStationNumber;
    }

    public String getCreatedIOTLead() {
        return createdIOTLead;
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

    public void setPremiseId(String premiseId) {
        this.premiseId = premiseId;
    }

    public void setPremisePlotNumber(String premisePlotNumber) {
        this.premisePlotNumber = premisePlotNumber;
    }

    public void setPremiseType(String premiseType) {
        this.premiseType = premiseType;
    }

    public void setPremiseAddress(String premiseAddress) {
        this.premiseAddress = premiseAddress;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setPremiseSubStationNumber(String premiseSubStationNumber) {
        this.premiseSubStationNumber = premiseSubStationNumber;
    }

    public void setCreatedIOTLead(String createdIOTLead) {
        this.createdIOTLead = createdIOTLead;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requests)) {
            return false;
        }
        Requests other = (Requests) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.USRequests[ id=" + id + " ]";
    }
    
}
