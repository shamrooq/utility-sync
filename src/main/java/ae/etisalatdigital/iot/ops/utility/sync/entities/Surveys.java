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
 * @author au_mobility
 */
@Entity
@Table(name = "US_Survey", catalog = "UTILITYSYNC", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Surveys.findAll", query = "SELECT m FROM Surveys m")
        ,@NamedQuery(name = "Surveys.findAllPOC", query = "SELECT m FROM Surveys m where m.surveyStatus = 'Completed' or m.surveyStatus = 'Failed'")
        ,@NamedQuery(name = "Surveys.findAllPOCCompleted", query = "SELECT m FROM Surveys m where m.surveyStatus = 'Completed'")
        ,@NamedQuery(name = "Surveys.findAllPOCFailed", query = "SELECT m FROM Surveys m where  m.surveyStatus = 'Failed'")
        ,@NamedQuery(name = "Surveys.findByUtilityNumber", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.SurveyDTO(m.id, m.utilityNumber, m.surveySurveyor, m.surveyStatus, m.createdDate, m.modifiedDate, m.surveyDate, m.emirateName, m.emirateRegione, m.substationNumber, m.vendorName, m.premiseId, m.premiseType, m.premiseBuildingName, m.address, m.additionalNotes, m.latitude, m.longitude, m.nbiotCNSRSPR, m.nbiotCNSSINR, m.nbiotSignalStrength, m.nbiotServingIdSite, m.nbiotServingIdCell, m.nbiotCNSRSPRAcceptableRange, m.nbiotCNSSINRAcceptableRange, m.nbiotSignalStrengthRange, m.crWMRequirement_01, m.crWMRequirement_02, m.crWMRequirement_03, m.crWMRequirement_04, m.crWMRequirement_05, m.crWMRequirement_06, m.crWMRequirement_07, m.crWMRequirement_08, m.crEMRequirement_01, m.crEMRequirement_02, m.crEMRequirement_03, m.crEMRequirement_04, m.crEMRequirement_05, m.crEMRequirement_06, m.crEMRequirement_07, m.crEMRequirement_08, m.psWMRequirement_01, m.psWMRequirement_02, m.psWMRequirement_03, m.psWMRequirement_04, m.psWMRequirement_05, m.psEMRequirement_01, m.psEMRequirement_02, m.psEMRequirement_03, m.psEMRequirement_04, m.psEMRequirement_05, m.metersWM_AMI, m.metersWM_NAMI, m.metersWM_MBUS, m.metersWM_WMBUS, m.metersWM_Others, m.metersWM_Lable_Others, m.metersEM_Lable_Others, m.metersEM_AMI, m.metersEM_NAMI, m.metersEM_DLMS, m.metersEM_Euridis, m.metersEM_Others, m.bomId, m.customerCode, m.contactPrimary, m.contactAdditional, m.cableLengthForEMeters, m.gatewaysTypeForEMeters, m.gatewaysNumberForEMeters, m.metersElectricPerGateway, m.cableLengthForWMeters, m.gatewaysTypeForWMeters, m.gatewaysNumberForWMeters, m.metersWaterPerGateway, m.surveyRejectReason, m.surveyEntitledAction) FROM Surveys m where m.utilityNumber = :utilityNumber")
})
public class Surveys implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "SURVEY_ID", nullable = false)
    private Long id;
    
    @Column(name = "BOM_ID")
    private Long bomId;
    
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "UTILITY_NUMBER", nullable = false, length = 50)
    private String utilityNumber;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Survey_Surveyor", nullable = false, length = 300)
    private String surveySurveyor;
    
    @Size(max = 50)
    @Column(name = "Survey_Status", length = 50)
    private String surveyStatus;
    
    @Column(name = "Created_Date")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date createdDate;
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "Survey_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date surveyDate;
    
    @Size(max = 500)
    @Column(name = "Emirate_Name", length = 500)
    private String emirateName;
    
    @Size(max = 200)
    @Column(name = "Emirate_Region", length = 200)
    private String emirateRegione;
    
    
    @Size(max = 200)
    @Column(name = "Substation_Number", length = 200)
    private String substationNumber;
    
    @Size(max = 50)
    @Column(name = "Vendor_Name", length = 50)
    private String vendorName;
    
    @Size(max = 50)
    @Column(name = "Customer_Code", length = 50)
    private String customerCode;
    
    @Size(max = 50)
    @Column(name = "Contact_Primary", length = 50)
    private String contactPrimary;
    
    @Size(max = 50)
    @Column(name = "Contact_Additional", length = 50)
    private String contactAdditional;
    
    @Size(max = 50)
    @Column(name = "Premise_Id", length = 50)
    private String premiseId;
    
    @Size(max = 200)
    @Column(name = "Premise_Type", length = 200)
    private String premiseType;
    
    
    
    @Size(max = 300)
    @Column(name = "Premise_Building_Name", length = 300)
    private String premiseBuildingName;
    
    @Column(name = "Full_Address")
    private String address;
    
    @Size(max = 4000)
    @Column(name = "Additional_Notes", length = 4000)
    private String additionalNotes;
    
    @Column(name = "Latitude",precision = 15, scale = 10)
    private BigDecimal latitude;
    @Column(name = "Longitude",precision = 15, scale = 10)
    private BigDecimal longitude;
    
    
    @Size(max = 50)
    @Column(name = "NBIOT_CNS_RSPR", length = 50)
    private String nbiotCNSRSPR;
    
    @Size(max = 50)
    @Column(name = "NBIOT_CNS_SINR", length = 50)
    private String nbiotCNSSINR;
    
    @Size(max = 50)
    @Column(name = "NBIOT_Signal_Strength", length = 50)
    private String nbiotSignalStrength;
    
    @Size(max = 50)
    @Column(name = "NBIOT_Serving_Id_Site", length = 50)
    private String nbiotServingIdSite;
    
    @Size(max = 50)
    @Column(name = "NBIOT_Serving_Id_Cell", length = 50)
    private String nbiotServingIdCell;
    
    @Size(max = 200)
    @Column(name = "Survey_Reject_Reason", length = 200)
    private String surveyRejectReason;
    
    @Size(max = 100)
    @Column(name = "Survey_Entitled_Action", length = 100)
    private String surveyEntitledAction;
    
    @Column(name = "NBIOT_CNS_RSPR_Acceptable_Range")
    private Boolean nbiotCNSRSPRAcceptableRange;
    
    @Column(name = "NBIOT_CNS_SINR_Acceptable_Range")
    private Boolean nbiotCNSSINRAcceptableRange;
    
    @Column(name = "NBIOT_3G4GSignal_Range")
    private Boolean nbiotSignalStrengthRange;
    
    
    @Column(name = "CR_WM_Requirement_01")
    private Boolean crWMRequirement_01;
    @Column(name = "CR_WM_Requirement_02")
    private Boolean crWMRequirement_02;
    @Column(name = "CR_WM_Requirement_03")
    private Boolean crWMRequirement_03;
    @Column(name = "CR_WM_Requirement_04")
    private Boolean crWMRequirement_04;
    @Column(name = "CR_WM_Requirement_05")
    private Boolean crWMRequirement_05;
    @Column(name = "CR_WM_Requirement_06")
    private Boolean crWMRequirement_06;
    @Column(name = "CR_WM_Requirement_07")
    private Boolean crWMRequirement_07;
    @Column(name = "CR_WM_Requirement_08")
    private Boolean crWMRequirement_08;
    @Column(name = "CR_EM_Requirement_01")
    private Boolean crEMRequirement_01;
    @Column(name = "CR_EM_Requirement_02")
    private Boolean crEMRequirement_02;
    @Column(name = "CR_EM_Requirement_03")
    private Boolean crEMRequirement_03;
    @Column(name = "CR_EM_Requirement_04")
    private Boolean crEMRequirement_04;
    @Column(name = "CR_EM_Requirement_05")
    private Boolean crEMRequirement_05;
    @Column(name = "CR_EM_Requirement_06")
    private Boolean crEMRequirement_06;
    @Column(name = "CR_EM_Requirement_07")
    private Boolean crEMRequirement_07;
    @Column(name = "CR_EM_Requirement_08")
    private Boolean crEMRequirement_08;
    
    
    @Column(name = "PS_WM_Requirement_01")
    private Boolean psWMRequirement_01;
    @Column(name = "PS_WM_Requirement_02")
    private Boolean psWMRequirement_02;
    @Column(name = "PS_WM_Requirement_03")
    private Boolean psWMRequirement_03;
    @Column(name = "PS_WM_Requirement_04")
    private Boolean psWMRequirement_04;
    @Column(name = "PS_WM_Requirement_05")
    private Boolean psWMRequirement_05;
    
    @Column(name = "PS_EM_Requirement_01")
    private Boolean psEMRequirement_01;
    @Column(name = "PS_EM_Requirement_02")
    private Boolean psEMRequirement_02;
    @Column(name = "PS_EM_Requirement_03")
    private Boolean psEMRequirement_03;
    @Column(name = "PS_EM_Requirement_04")
    private Boolean psEMRequirement_04;
    @Column(name = "PS_EM_Requirement_05")
    private Boolean psEMRequirement_05;
    
    @Column(name = "Meters_WM_AMI")
    private Integer metersWM_AMI;
    @Column(name = "Meters_WM_NAMI")
    private Integer metersWM_NAMI;
    @Column(name = "Meters_WM_MBus")
    private Integer metersWM_MBUS;
    @Column(name = "Meters_WM_WMBus")
    private Integer metersWM_WMBUS;
    @Column(name = "Meters_WM_Others")
    private Integer metersWM_Others;
    @Column(name = "Meters_WM_Others_Label")
    private String metersWM_Lable_Others;
    
    @Column(name = "Meters_EM_AMI")
    private Integer metersEM_AMI;
    @Column(name = "Meters_EM_NAMI")
    private Integer metersEM_NAMI;
    @Column(name = "Meters_EM_DLMS")
    private Integer metersEM_DLMS;
    @Column(name = "Meters_EM_Euridis")
    private Integer metersEM_Euridis;
    @Column(name = "Meters_EM_Others")
    private Integer metersEM_Others;
    @Column(name = "Meters_EM_Others_Label")
    private String metersEM_Lable_Others;
    
    @Column(name = "Etismation_Cable_Length_Electric")
    private String cableLengthForEMeters;
    @Column(name = "Etismation_GW_Type_Electric")
    private String gatewaysTypeForEMeters;
    @Column(name = "Etismation_GW_No_Electric")
    private Integer gatewaysNumberForEMeters;
    @Column(name = "Etismation_EM_Per_GW")
    private Integer metersElectricPerGateway;
    
    @Column(name = "Etismation_Cable_Length_Water")
    private String cableLengthForWMeters;
    @Column(name = "Etismation_GW_Type_Water")
    private String gatewaysTypeForWMeters;
    @Column(name = "Etismation_GW_No_Water")
    private Integer gatewaysNumberForWMeters;
    @Column(name = "Etismation_WM_Per_GW")
    private Integer metersWaterPerGateway;
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public Long getId() {
        return id;
    }

    public Long getBomId() {
        return bomId;
    }
    
    
    public String getUtilityNumber() {
        return utilityNumber;
    }

    public String getSurveySurveyor() {
        return surveySurveyor;
    }

    public String getSurveyStatus() {
        return surveyStatus;
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

    public String getEmirateName() {
        return emirateName;
    }

    public String getEmirateRegione() {
        return emirateRegione;
    }

    public String getSubstationNumber() {
        return substationNumber;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getPremiseId() {
        return premiseId;
    }

    public String getPremiseType() {
        return premiseType;
    }

    public String getPremiseBuildingName() {
        return premiseBuildingName;
    }

    public String getAddress() {
        return address;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public String getNbiotCNSRSPR() {
        return nbiotCNSRSPR;
    }

    public String getNbiotCNSSINR() {
        return nbiotCNSSINR;
    }

    public String getNbiotSignalStrength() {
        return nbiotSignalStrength;
    }

    public String getNbiotServingIdSite() {
        return nbiotServingIdSite;
    }

    public String getNbiotServingIdCell() {
        return nbiotServingIdCell;
    }
    
    

    public Boolean getNbiotCNSRSPRAcceptableRange() {
        return nbiotCNSRSPRAcceptableRange;
    }
    
    public Boolean getNbiotCNSSINRAcceptableRange() {
        return nbiotCNSSINRAcceptableRange;
    }

    public Boolean getNbiotSignalStrengthRange() {
        return nbiotSignalStrengthRange;
    }
    
    

    public Boolean getCrWMRequirement_01() {
        return crWMRequirement_01;
    }

    public Boolean getCrWMRequirement_02() {
        return crWMRequirement_02;
    }

    public Boolean getCrWMRequirement_03() {
        return crWMRequirement_03;
    }

    public Boolean getCrWMRequirement_04() {
        return crWMRequirement_04;
    }

    public Boolean getCrWMRequirement_05() {
        return crWMRequirement_05;
    }

    public Boolean getCrWMRequirement_06() {
        return crWMRequirement_06;
    }

    public Boolean getCrWMRequirement_07() {
        return crWMRequirement_07;
    }

    public Boolean getCrWMRequirement_08() {
        return crWMRequirement_08;
    }
    
    

    public Boolean getCrEMRequirement_01() {
        return crEMRequirement_01;
    }

    public Boolean getCrEMRequirement_02() {
        return crEMRequirement_02;
    }

    public Boolean getCrEMRequirement_03() {
        return crEMRequirement_03;
    }

    public Boolean getCrEMRequirement_04() {
        return crEMRequirement_04;
    }

    public Boolean getCrEMRequirement_05() {
        return crEMRequirement_05;
    }

    public Boolean getCrEMRequirement_07() {
        return crEMRequirement_07;
    }

    public Boolean getCrEMRequirement_08() {
        return crEMRequirement_08;
    }
    
    

    public Boolean getCrEMRequirement_06() {
        return crEMRequirement_06;
    }

    public Boolean getPsWMRequirement_01() {
        return psWMRequirement_01;
    }

    public Boolean getPsWMRequirement_02() {
        return psWMRequirement_02;
    }

    public Boolean getPsWMRequirement_03() {
        return psWMRequirement_03;
    }

    public Boolean getPsWMRequirement_04() {
        return psWMRequirement_04;
    }

    public Boolean getPsWMRequirement_05() {
        return psWMRequirement_05;
    }

    

    public Boolean getPsEMRequirement_01() {
        return psEMRequirement_01;
    }

    public Boolean getPsEMRequirement_02() {
        return psEMRequirement_02;
    }

    public Boolean getPsEMRequirement_03() {
        return psEMRequirement_03;
    }

    public Boolean getPsEMRequirement_04() {
        return psEMRequirement_04;
    }

    public Boolean getPsEMRequirement_05() {
        return psEMRequirement_05;
    }

    public Integer getMetersWM_AMI() {
        return metersWM_AMI;
    }

    public Integer getMetersWM_NAMI() {
        return metersWM_NAMI;
    }

    public Integer getMetersWM_MBUS() {
        return metersWM_MBUS;
    }

    public Integer getMetersWM_WMBUS() {
        return metersWM_WMBUS;
    }

    public Integer getMetersWM_Others() {
        return metersWM_Others;
    }

    public String getMetersWM_Lable_Others() {
        return metersWM_Lable_Others;
    }
    
    

    public Integer getMetersEM_AMI() {
        return metersEM_AMI;
    }

    public Integer getMetersEM_NAMI() {
        return metersEM_NAMI;
    }

    public Integer getMetersEM_DLMS() {
        return metersEM_DLMS;
    }

    public Integer getMetersEM_Euridis() {
        return metersEM_Euridis;
    }

    public Integer getMetersEM_Others() {
        return metersEM_Others;
    }

    public String getMetersEM_Lable_Others() {
        return metersEM_Lable_Others;
    }
    
    
    
    public String getCustomerCode() {
        return customerCode;
    }

    public String getContactPrimary() {
        return contactPrimary;
    }

    public String getContactAdditional() {
        return contactAdditional;
    }

    public String getCableLengthForEMeters() {
        return cableLengthForEMeters;
    }

    public String getGatewaysTypeForEMeters() {
        return gatewaysTypeForEMeters;
    }

    public Integer getGatewaysNumberForEMeters() {
        return gatewaysNumberForEMeters;
    }

    public Integer getMetersElectricPerGateway() {
        return metersElectricPerGateway;
    }

    public String getCableLengthForWMeters() {
        return cableLengthForWMeters;
    }

    public String getGatewaysTypeForWMeters() {
        return gatewaysTypeForWMeters;
    }

    public Integer getGatewaysNumberForWMeters() {
        return gatewaysNumberForWMeters;
    }

    public Integer getMetersWaterPerGateway() {
        return metersWaterPerGateway;
    }

    public String getSurveyRejectReason() {
        return surveyRejectReason;
    }

    public String getSurveyEntitledAction() {
        return surveyEntitledAction;
    }
    
    
    
    
    /**
     * @param id
     * 
     *******/
    
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setUtilityNumber(String utilityNumber) {
        this.utilityNumber = utilityNumber;
    }

    public void setSurveySurveyor(String surveySurveyor) {
        this.surveySurveyor = surveySurveyor;
    }

    public void setSurveyStatus(String surveyStatus) {
        this.surveyStatus = surveyStatus;
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

    public void setEmirateName(String emirateName) {
        this.emirateName = emirateName;
    }

    public void setEmirateRegione(String emirateRegione) {
        this.emirateRegione = emirateRegione;
    }

    public void setSubstationNumber(String substationNumber) {
        this.substationNumber = substationNumber;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public void setPremiseId(String premiseId) {
        this.premiseId = premiseId;
    }

    public void setPremiseType(String premiseType) {
        this.premiseType = premiseType;
    }

    public void setPremiseBuildingName(String premiseBuildingName) {
        this.premiseBuildingName = premiseBuildingName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setNbiotCNSRSPR(String nbiotCNSRSPR) {
        this.nbiotCNSRSPR = nbiotCNSRSPR;
    }

    public void setNbiotCNSSINR(String nbiotCNSSINR) {
        this.nbiotCNSSINR = nbiotCNSSINR;
    }

    public void setNbiotSignalStrength(String nbiotSignalStrength) {
        this.nbiotSignalStrength = nbiotSignalStrength;
    }

    public void setNbiotServingIdSite(String nbiotServingIdSite) {
        this.nbiotServingIdSite = nbiotServingIdSite;
    }

    public void setNbiotServingIdCell(String nbiotServingIdCell) {
        this.nbiotServingIdCell = nbiotServingIdCell;
    }
    
    

    public void setNbiotCNSRSPRAcceptableRange(Boolean nbiotCNSRSPRAcceptableRange) {
        this.nbiotCNSRSPRAcceptableRange = nbiotCNSRSPRAcceptableRange;
    }
    
    

    public void setNbiotCNSSINRAcceptableRange(Boolean nbiotCNSSINRAcceptableRange) {
        this.nbiotCNSSINRAcceptableRange = nbiotCNSSINRAcceptableRange;
    }

    public void setNbiotSignalStrengthRange(Boolean nbiotSignalStrengthRange) {
        this.nbiotSignalStrengthRange = nbiotSignalStrengthRange;
    }
    
    

    public void setCrWMRequirement_01(Boolean crWMRequirement_01) {
        this.crWMRequirement_01 = crWMRequirement_01;
    }

    public void setCrWMRequirement_02(Boolean crWMRequirement_02) {
        this.crWMRequirement_02 = crWMRequirement_02;
    }

    public void setCrWMRequirement_03(Boolean crWMRequirement_03) {
        this.crWMRequirement_03 = crWMRequirement_03;
    }

    public void setCrWMRequirement_04(Boolean crWMRequirement_04) {
        this.crWMRequirement_04 = crWMRequirement_04;
    }

    public void setCrWMRequirement_05(Boolean crWMRequirement_05) {
        this.crWMRequirement_05 = crWMRequirement_05;
    }

    public void setCrWMRequirement_06(Boolean crWMRequirement_06) {
        this.crWMRequirement_06 = crWMRequirement_06;
        
    }

    public void setCrWMRequirement_07(Boolean crWMRequirement_07) {
        this.crWMRequirement_07 = crWMRequirement_07;
    }

    public void setCrWMRequirement_08(Boolean crWMRequirement_08) {
        this.crWMRequirement_08 = crWMRequirement_08;
    }

    
    
    public void setCrEMRequirement_01(Boolean crEMRequirement_01) {
        this.crEMRequirement_01 = crEMRequirement_01;
    }

    public void setCrEMRequirement_02(Boolean crEMRequirement_02) {
        this.crEMRequirement_02 = crEMRequirement_02;
    }

    public void setCrEMRequirement_03(Boolean crEMRequirement_03) {
        this.crEMRequirement_03 = crEMRequirement_03;
    }

    public void setCrEMRequirement_04(Boolean crEMRequirement_04) {
        this.crEMRequirement_04 = crEMRequirement_04;
    }

    public void setCrEMRequirement_05(Boolean crEMRequirement_05) {
        this.crEMRequirement_05 = crEMRequirement_05;
    }

    public void setCrEMRequirement_06(Boolean crEMRequirement_06) {
        this.crEMRequirement_06 = crEMRequirement_06;
    }

    public void setCrEMRequirement_07(Boolean crEMRequirement_07) {
        this.crEMRequirement_07 = crEMRequirement_07;
    }

    public void setCrEMRequirement_08(Boolean crEMRequirement_08) {
        this.crEMRequirement_08 = crEMRequirement_08;
    }
    
    

    public void setPsWMRequirement_01(Boolean psWMRequirement_01) {
        this.psWMRequirement_01 = psWMRequirement_01;
    }

    public void setPsWMRequirement_02(Boolean psWMRequirement_02) {
        this.psWMRequirement_02 = psWMRequirement_02;
    }

    public void setPsWMRequirement_03(Boolean psWMRequirement_03) {
        this.psWMRequirement_03 = psWMRequirement_03;
    }

    public void setPsWMRequirement_04(Boolean psWMRequirement_04) {
        this.psWMRequirement_04 = psWMRequirement_04;
    }

    public void setPsWMRequirement_05(Boolean psWMRequirement_05) {
        this.psWMRequirement_05 = psWMRequirement_05;
    }

    

    public void setPsEMRequirement_01(Boolean psEMRequirement_01) {
        this.psEMRequirement_01 = psEMRequirement_01;
    }

    public void setPsEMRequirement_02(Boolean psEMRequirement_02) {
        this.psEMRequirement_02 = psEMRequirement_02;
    }

    public void setPsEMRequirement_03(Boolean psEMRequirement_03) {
        this.psEMRequirement_03 = psEMRequirement_03;
    }

    public void setPsEMRequirement_04(Boolean psEMRequirement_04) {
        this.psEMRequirement_04 = psEMRequirement_04;
    }

    public void setPsEMRequirement_05(Boolean psEMRequirement_05) {
        this.psEMRequirement_05 = psEMRequirement_05;
    }

    public void setMetersWM_AMI(Integer metersWM_AMI) {
        this.metersWM_AMI = metersWM_AMI;
    }

    public void setMetersWM_NAMI(Integer metersWM_NAMI) {
        this.metersWM_NAMI = metersWM_NAMI;
    }

    public void setMetersWM_MBUS(Integer metersWM_MBUS) {
        this.metersWM_MBUS = metersWM_MBUS;
    }

    public void setMetersWM_WMBUS(Integer metersWM_WMBUS) {
        this.metersWM_WMBUS = metersWM_WMBUS;
    }

    public void setMetersWM_Others(Integer metersWM_Others) {
        this.metersWM_Others = metersWM_Others;
    }

    public void setMetersWM_Lable_Others(String metersWM_Lable_Others) {
        this.metersWM_Lable_Others = metersWM_Lable_Others;
    }
    
    
    
    public void setMetersEM_AMI(Integer metersEM_AMI) {
        this.metersEM_AMI = metersEM_AMI;
    }

    public void setMetersEM_NAMI(Integer metersEM_NAMI) {
        this.metersEM_NAMI = metersEM_NAMI;
    }

    public void setMetersEM_DLMS(Integer metersEM_DLMS) {
        this.metersEM_DLMS = metersEM_DLMS;
    }

    public void setMetersEM_Euridis(Integer metersEM_Euridis) {
        this.metersEM_Euridis = metersEM_Euridis;
    }

    public void setMetersEM_Others(Integer metersEM_Others) {
        this.metersEM_Others = metersEM_Others;
    }

    public void setMetersEM_Lable_Others(String metersEM_Lable_Others) {
        this.metersEM_Lable_Others = metersEM_Lable_Others;
    }

    
    

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public void setContactPrimary(String contactPrimary) {
        this.contactPrimary = contactPrimary;
    }

    public void setContactAdditional(String contactAdditional) {
        this.contactAdditional = contactAdditional;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public void setCableLengthForEMeters(String cableLengthForEMeters) {
        this.cableLengthForEMeters = cableLengthForEMeters;
    }

    public void setGatewaysTypeForEMeters(String gatewaysTypeForEMeters) {
        this.gatewaysTypeForEMeters = gatewaysTypeForEMeters;
    }

    public void setGatewaysNumberForEMeters(Integer gatewaysNumberForEMeters) {
        this.gatewaysNumberForEMeters = gatewaysNumberForEMeters;
    }

    public void setMetersElectricPerGateway(Integer metersElectricPerGateway) {
        this.metersElectricPerGateway = metersElectricPerGateway;
    }

    public void setCableLengthForWMeters(String cableLengthForWMeters) {
        this.cableLengthForWMeters = cableLengthForWMeters;
    }

    public void setGatewaysTypeForWMeters(String gatewaysTypeForWMeters) {
        this.gatewaysTypeForWMeters = gatewaysTypeForWMeters;
    }

    public void setGatewaysNumberForWMeters(Integer gatewaysNumberForWMeters) {
        this.gatewaysNumberForWMeters = gatewaysNumberForWMeters;
    }

    public void setMetersWaterPerGateway(Integer metersWaterPerGateway) {
        this.metersWaterPerGateway = metersWaterPerGateway;
    }

    public void setSurveyRejectReason(String surveyRejectReason) {
        this.surveyRejectReason = surveyRejectReason;
    }

    public void setSurveyEntitledAction(String surveyEntitledAction) {
        this.surveyEntitledAction = surveyEntitledAction;
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
        if (!(object instanceof Surveys)) {
            return false;
        }
        Surveys other = (Surveys) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.Surveys[ id=" + id + " ]";
    }
    
}
