/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.validation.constraints.Size;


/**
 *
 * @author au_mobility
 */
public class SurveyDTO {
    
    private Long id;
    
    
    private String utilityNumber;
    
    
    private String surveySurveyor;
    
    
    private String surveyStatus;
    
    
    private Date createdDate;
    
    private Date modifiedDate;
    
    private Date surveyDate;
    
    
    private String emirateName;
    
    
    private String emirateRegione;
    
    
    
    private String substationNumber;
    
    
    private String vendorName;
    
    
    private String premiseId;
    
    
    private String premiseType;
    
    
    private String premiseBuildingName;
    
    
    private String address;
    
    
    private String additionalNotes;
    
    
    private BigDecimal latitude;
    private BigDecimal longitude;
    
    
    private String nbiotCNSRSPR;
    private String nbiotCNSSINR;
    private String nbiotSignalStrength;
    private String nbiotServingIdSite;
    private String nbiotServingIdCell;
    
    private Boolean nbiotCNSRSPRAcceptableRange;
    private Boolean nbiotCNSSINRAcceptableRange;
    private Boolean nbiotSignalStrengthRange; 
    
    private Boolean crWMRequirement_01;
    
    private Boolean crWMRequirement_02;
    
    private Boolean crWMRequirement_03;
    
    private Boolean crWMRequirement_04;
    
    private Boolean crWMRequirement_05;
    
    private Boolean crWMRequirement_06;
    
    private Boolean crWMRequirement_07;
    
    private Boolean crWMRequirement_08;
    
    private Boolean crEMRequirement_01;
    
    private Boolean crEMRequirement_02;
    
    private Boolean crEMRequirement_03;
    
    private Boolean crEMRequirement_04;
    
    private Boolean crEMRequirement_05;
    
    private Boolean crEMRequirement_06;
    
    private Boolean crEMRequirement_07;
    
    private Boolean crEMRequirement_08;
    
    
    private Boolean psWMRequirement_01;
    
    private Boolean psWMRequirement_02;
    
    private Boolean psWMRequirement_03;
    
    private Boolean psWMRequirement_04;
    
    private Boolean psWMRequirement_05;
    
    
    
    private Boolean psEMRequirement_01;
    
    private Boolean psEMRequirement_02;
    
    private Boolean psEMRequirement_03;
    
    private Boolean psEMRequirement_04;
    
    private Boolean psEMRequirement_05;
    
    
    private Integer metersWM_AMI;
    
    private Integer metersWM_NAMI;
    
    private Integer metersWM_MBUS;
    
    private Integer metersWM_WMBUS;
    
    private Integer metersWM_Others;
    
    private String metersWM_Lable_Others;
    
    private String metersEM_Lable_Others;
    
    private Integer metersEM_AMI;
    
    private Integer metersEM_NAMI;
    
    private Integer metersEM_DLMS;
    
    private Integer metersEM_Euridis;
    
    private Integer metersEM_Others;
    
    private Long bomId;
    
    
    private String customerCode;
    
    private String contactPrimary;
    
    private String contactAdditional;
    
    private String cableLengthForEMeters;
    private String gatewaysTypeForEMeters;
    private Integer gatewaysNumberForEMeters;
    private Integer metersElectricPerGateway;
    
    private String cableLengthForWMeters;
    private String gatewaysTypeForWMeters;
    private Integer gatewaysNumberForWMeters;
    private Integer metersWaterPerGateway;
    
    private String surveyRejectReason;
    private String surveyEntitledAction;
    
    public SurveyDTO(){}

    
    public SurveyDTO(Long id, String utilityNumber, String surveySurveyor, String surveyStatus, Date createdDate, Date modifiedDate, Date surveyDate, String emirateName, String emirateRegione, String substationNumber, String vendorName, String premiseId, String premiseType, String premiseBuildingName, String address, String additionalNotes, BigDecimal latitude, BigDecimal longitude, String nbiotCNSRSPR, String nbiotCNSSINR, Boolean nbiotCNSRSPRAcceptableRange, Boolean nbiotCNSSINRAcceptableRange, Boolean crWMRequirement_01, Boolean crWMRequirement_02, Boolean crWMRequirement_03, Boolean crWMRequirement_04, Boolean crWMRequirement_05, Boolean crWMRequirement_06, Boolean crEMRequirement_01, Boolean crEMRequirement_02, Boolean crEMRequirement_03, Boolean crEMRequirement_04, Boolean crEMRequirement_05, Boolean crEMRequirement_06, Boolean psWMRequirement_01, Boolean psWMRequirement_02, Boolean psWMRequirement_03, Boolean psWMRequirement_04, Boolean psWMRequirement_05, Boolean psEMRequirement_01, Boolean psEMRequirement_02, Boolean psEMRequirement_03, Boolean psEMRequirement_04, Boolean psEMRequirement_05, Integer metersWM_AMI, Integer metersWM_NAMI, Integer metersWM_MBUS, Integer metersWM_WMBUS, Integer metersWM_Others, Integer metersEM_AMI, Integer metersEM_NAMI, Integer metersEM_DLMS, Integer metersEM_Euridis, Integer metersEM_Others, Long bomId, String customerCode, String contactPrimary, String contactAdditional) {
        this.id = id;
        this.utilityNumber = utilityNumber;
        this.surveySurveyor = surveySurveyor;
        this.surveyStatus = surveyStatus;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.surveyDate = surveyDate;
        this.emirateName = emirateName;
        this.emirateRegione = emirateRegione;
        this.substationNumber = substationNumber;
        this.vendorName = vendorName;
        this.premiseId = premiseId;
        this.premiseType = premiseType;
        this.premiseBuildingName = premiseBuildingName;
        this.address = address;
        this.additionalNotes = additionalNotes;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nbiotCNSRSPR = nbiotCNSRSPR;
        this.nbiotCNSSINR = nbiotCNSSINR;
        this.nbiotCNSRSPRAcceptableRange = nbiotCNSRSPRAcceptableRange;
        this.nbiotCNSSINRAcceptableRange = nbiotCNSSINRAcceptableRange;
        this.crWMRequirement_01 = crWMRequirement_01;
        this.crWMRequirement_02 = crWMRequirement_02;
        this.crWMRequirement_03 = crWMRequirement_03;
        this.crWMRequirement_04 = crWMRequirement_04;
        this.crWMRequirement_05 = crWMRequirement_05;
        this.crWMRequirement_06 = crWMRequirement_06;
        this.crEMRequirement_01 = crEMRequirement_01;
        this.crEMRequirement_02 = crEMRequirement_02;
        this.crEMRequirement_03 = crEMRequirement_03;
        this.crEMRequirement_04 = crEMRequirement_04;
        this.crEMRequirement_05 = crEMRequirement_05;
        this.crEMRequirement_06 = crEMRequirement_06;
        this.psWMRequirement_01 = psWMRequirement_01;
        this.psWMRequirement_02 = psWMRequirement_02;
        this.psWMRequirement_03 = psWMRequirement_03;
        this.psWMRequirement_04 = psWMRequirement_04;
        this.psWMRequirement_05 = psWMRequirement_05;
        this.psEMRequirement_01 = psEMRequirement_01;
        this.psEMRequirement_02 = psEMRequirement_02;
        this.psEMRequirement_03 = psEMRequirement_03;
        this.psEMRequirement_04 = psEMRequirement_04;
        this.psEMRequirement_05 = psEMRequirement_05;
        this.metersWM_AMI = metersWM_AMI;
        this.metersWM_NAMI = metersWM_NAMI;
        this.metersWM_MBUS = metersWM_MBUS;
        this.metersWM_WMBUS = metersWM_WMBUS;
        this.metersWM_Others = metersWM_Others;
        this.metersEM_AMI = metersEM_AMI;
        this.metersEM_NAMI = metersEM_NAMI;
        this.metersEM_DLMS = metersEM_DLMS;
        this.metersEM_Euridis = metersEM_Euridis;
        this.metersEM_Others = metersEM_Others;
        this.bomId = bomId;
        this.customerCode = customerCode;
        this.contactPrimary = contactPrimary;
        this.contactAdditional = contactAdditional;
    }

    public SurveyDTO(Long id, String utilityNumber, String surveySurveyor, String surveyStatus, Date createdDate, Date modifiedDate, Date surveyDate, String emirateName, String emirateRegione, String substationNumber, String vendorName, String premiseId, String premiseType, String premiseBuildingName, String address, String additionalNotes, BigDecimal latitude, BigDecimal longitude, String nbiotCNSRSPR, String nbiotCNSSINR, String nbiotSignalStrength, String nbiotServingIdSite, String nbiotServingIdCell, Boolean nbiotCNSRSPRAcceptableRange, Boolean nbiotCNSSINRAcceptableRange, Boolean nbiotSignalStrengthRange, Boolean crWMRequirement_01, Boolean crWMRequirement_02, Boolean crWMRequirement_03, Boolean crWMRequirement_04, Boolean crWMRequirement_05, Boolean crWMRequirement_06, Boolean crWMRequirement_07, Boolean crWMRequirement_08, Boolean crEMRequirement_01, Boolean crEMRequirement_02, Boolean crEMRequirement_03, Boolean crEMRequirement_04, Boolean crEMRequirement_05, Boolean crEMRequirement_06, Boolean crEMRequirement_07, Boolean crEMRequirement_08, Boolean psWMRequirement_01, Boolean psWMRequirement_02, Boolean psWMRequirement_03, Boolean psWMRequirement_04, Boolean psWMRequirement_05, Boolean psEMRequirement_01, Boolean psEMRequirement_02, Boolean psEMRequirement_03, Boolean psEMRequirement_04, Boolean psEMRequirement_05, Integer metersWM_AMI, Integer metersWM_NAMI, Integer metersWM_MBUS, Integer metersWM_WMBUS, Integer metersWM_Others, String metersWM_Lable_Others, Integer metersEM_AMI, Integer metersEM_NAMI, Integer metersEM_DLMS, Integer metersEM_Euridis, Integer metersEM_Others, String metersEM_Lable_Others, Long bomId, String customerCode, String contactPrimary, String contactAdditional) {
        this.id = id;
        this.utilityNumber = utilityNumber;
        this.surveySurveyor = surveySurveyor;
        this.surveyStatus = surveyStatus;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.surveyDate = surveyDate;
        this.emirateName = emirateName;
        this.emirateRegione = emirateRegione;
        this.substationNumber = substationNumber;
        this.vendorName = vendorName;
        this.premiseId = premiseId;
        this.premiseType = premiseType;
        this.premiseBuildingName = premiseBuildingName;
        this.address = address;
        this.additionalNotes = additionalNotes;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nbiotCNSRSPR = nbiotCNSRSPR;
        this.nbiotCNSSINR = nbiotCNSSINR;
        this.nbiotSignalStrength = nbiotSignalStrength;
        this.nbiotServingIdSite = nbiotServingIdSite;
        this.nbiotServingIdCell = nbiotServingIdCell;
        this.nbiotCNSRSPRAcceptableRange = nbiotCNSRSPRAcceptableRange;
        this.nbiotCNSSINRAcceptableRange = nbiotCNSSINRAcceptableRange;
        this.nbiotSignalStrengthRange = nbiotSignalStrengthRange;
        this.crWMRequirement_01 = crWMRequirement_01;
        this.crWMRequirement_02 = crWMRequirement_02;
        this.crWMRequirement_03 = crWMRequirement_03;
        this.crWMRequirement_04 = crWMRequirement_04;
        this.crWMRequirement_05 = crWMRequirement_05;
        this.crWMRequirement_06 = crWMRequirement_06;
        this.crWMRequirement_07 = crWMRequirement_07;
        this.crWMRequirement_08 = crWMRequirement_08;
        this.crEMRequirement_01 = crEMRequirement_01;
        this.crEMRequirement_02 = crEMRequirement_02;
        this.crEMRequirement_03 = crEMRequirement_03;
        this.crEMRequirement_04 = crEMRequirement_04;
        this.crEMRequirement_05 = crEMRequirement_05;
        this.crEMRequirement_06 = crEMRequirement_06;
        this.crEMRequirement_07 = crEMRequirement_07;
        this.crEMRequirement_08 = crEMRequirement_08;
        this.psWMRequirement_01 = psWMRequirement_01;
        this.psWMRequirement_02 = psWMRequirement_02;
        this.psWMRequirement_03 = psWMRequirement_03;
        this.psWMRequirement_04 = psWMRequirement_04;
        this.psWMRequirement_05 = psWMRequirement_05;
        this.psEMRequirement_01 = psEMRequirement_01;
        this.psEMRequirement_02 = psEMRequirement_02;
        this.psEMRequirement_03 = psEMRequirement_03;
        this.psEMRequirement_04 = psEMRequirement_04;
        this.psEMRequirement_05 = psEMRequirement_05;
        this.metersWM_AMI = metersWM_AMI;
        this.metersWM_NAMI = metersWM_NAMI;
        this.metersWM_MBUS = metersWM_MBUS;
        this.metersWM_WMBUS = metersWM_WMBUS;
        this.metersWM_Others = metersWM_Others;
        this.metersWM_Lable_Others = metersWM_Lable_Others;
        this.metersEM_Lable_Others = metersEM_Lable_Others;
        this.metersEM_AMI = metersEM_AMI;
        this.metersEM_NAMI = metersEM_NAMI;
        this.metersEM_DLMS = metersEM_DLMS;
        this.metersEM_Euridis = metersEM_Euridis;
        this.metersEM_Others = metersEM_Others;
        this.bomId = bomId;
        this.customerCode = customerCode;
        this.contactPrimary = contactPrimary;
        this.contactAdditional = contactAdditional;
    }

    public SurveyDTO(Long id, String utilityNumber, String surveySurveyor, String surveyStatus, Date createdDate, Date modifiedDate, Date surveyDate, String emirateName, String emirateRegione, String substationNumber, String vendorName, String premiseId, String premiseType, String premiseBuildingName, String address, String additionalNotes, BigDecimal latitude, BigDecimal longitude, String nbiotCNSRSPR, String nbiotCNSSINR, String nbiotSignalStrength, String nbiotServingIdSite, String nbiotServingIdCell, Boolean nbiotCNSRSPRAcceptableRange, Boolean nbiotCNSSINRAcceptableRange, Boolean nbiotSignalStrengthRange, Boolean crWMRequirement_01, Boolean crWMRequirement_02, Boolean crWMRequirement_03, Boolean crWMRequirement_04, Boolean crWMRequirement_05, Boolean crWMRequirement_06, Boolean crWMRequirement_07, Boolean crWMRequirement_08, Boolean crEMRequirement_01, Boolean crEMRequirement_02, Boolean crEMRequirement_03, Boolean crEMRequirement_04, Boolean crEMRequirement_05, Boolean crEMRequirement_06, Boolean crEMRequirement_07, Boolean crEMRequirement_08, Boolean psWMRequirement_01, Boolean psWMRequirement_02, Boolean psWMRequirement_03, Boolean psWMRequirement_04, Boolean psWMRequirement_05, Boolean psEMRequirement_01, Boolean psEMRequirement_02, Boolean psEMRequirement_03, Boolean psEMRequirement_04, Boolean psEMRequirement_05, Integer metersWM_AMI, Integer metersWM_NAMI, Integer metersWM_MBUS, Integer metersWM_WMBUS, Integer metersWM_Others, String metersWM_Lable_Others, String metersEM_Lable_Others, Integer metersEM_AMI, Integer metersEM_NAMI, Integer metersEM_DLMS, Integer metersEM_Euridis, Integer metersEM_Others, Long bomId, String customerCode, String contactPrimary, String contactAdditional, String cableLengthForEMeters, String gatewaysTypeForEMeters, Integer gatewaysNumberForEMeters, Integer metersElectricPerGateway, String cableLengthForWMeters, String gatewaysTypeForWMeters, Integer gatewaysNumberForWMeters, Integer metersWaterPerGateway) {
        this.id = id;
        this.utilityNumber = utilityNumber;
        this.surveySurveyor = surveySurveyor;
        this.surveyStatus = surveyStatus;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.surveyDate = surveyDate;
        this.emirateName = emirateName;
        this.emirateRegione = emirateRegione;
        this.substationNumber = substationNumber;
        this.vendorName = vendorName;
        this.premiseId = premiseId;
        this.premiseType = premiseType;
        this.premiseBuildingName = premiseBuildingName;
        this.address = address;
        this.additionalNotes = additionalNotes;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nbiotCNSRSPR = nbiotCNSRSPR;
        this.nbiotCNSSINR = nbiotCNSSINR;
        this.nbiotSignalStrength = nbiotSignalStrength;
        this.nbiotServingIdSite = nbiotServingIdSite;
        this.nbiotServingIdCell = nbiotServingIdCell;
        this.nbiotCNSRSPRAcceptableRange = nbiotCNSRSPRAcceptableRange;
        this.nbiotCNSSINRAcceptableRange = nbiotCNSSINRAcceptableRange;
        this.nbiotSignalStrengthRange = nbiotSignalStrengthRange;
        this.crWMRequirement_01 = crWMRequirement_01;
        this.crWMRequirement_02 = crWMRequirement_02;
        this.crWMRequirement_03 = crWMRequirement_03;
        this.crWMRequirement_04 = crWMRequirement_04;
        this.crWMRequirement_05 = crWMRequirement_05;
        this.crWMRequirement_06 = crWMRequirement_06;
        this.crWMRequirement_07 = crWMRequirement_07;
        this.crWMRequirement_08 = crWMRequirement_08;
        this.crEMRequirement_01 = crEMRequirement_01;
        this.crEMRequirement_02 = crEMRequirement_02;
        this.crEMRequirement_03 = crEMRequirement_03;
        this.crEMRequirement_04 = crEMRequirement_04;
        this.crEMRequirement_05 = crEMRequirement_05;
        this.crEMRequirement_06 = crEMRequirement_06;
        this.crEMRequirement_07 = crEMRequirement_07;
        this.crEMRequirement_08 = crEMRequirement_08;
        this.psWMRequirement_01 = psWMRequirement_01;
        this.psWMRequirement_02 = psWMRequirement_02;
        this.psWMRequirement_03 = psWMRequirement_03;
        this.psWMRequirement_04 = psWMRequirement_04;
        this.psWMRequirement_05 = psWMRequirement_05;
        this.psEMRequirement_01 = psEMRequirement_01;
        this.psEMRequirement_02 = psEMRequirement_02;
        this.psEMRequirement_03 = psEMRequirement_03;
        this.psEMRequirement_04 = psEMRequirement_04;
        this.psEMRequirement_05 = psEMRequirement_05;
        this.metersWM_AMI = metersWM_AMI;
        this.metersWM_NAMI = metersWM_NAMI;
        this.metersWM_MBUS = metersWM_MBUS;
        this.metersWM_WMBUS = metersWM_WMBUS;
        this.metersWM_Others = metersWM_Others;
        this.metersWM_Lable_Others = metersWM_Lable_Others;
        this.metersEM_Lable_Others = metersEM_Lable_Others;
        this.metersEM_AMI = metersEM_AMI;
        this.metersEM_NAMI = metersEM_NAMI;
        this.metersEM_DLMS = metersEM_DLMS;
        this.metersEM_Euridis = metersEM_Euridis;
        this.metersEM_Others = metersEM_Others;
        this.bomId = bomId;
        this.customerCode = customerCode;
        this.contactPrimary = contactPrimary;
        this.contactAdditional = contactAdditional;
        this.cableLengthForEMeters = cableLengthForEMeters;
        this.gatewaysTypeForEMeters = gatewaysTypeForEMeters;
        this.gatewaysNumberForEMeters = gatewaysNumberForEMeters;
        this.metersElectricPerGateway = metersElectricPerGateway;
        this.cableLengthForWMeters = cableLengthForWMeters;
        this.gatewaysTypeForWMeters = gatewaysTypeForWMeters;
        this.gatewaysNumberForWMeters = gatewaysNumberForWMeters;
        this.metersWaterPerGateway = metersWaterPerGateway;
    }

    public SurveyDTO(Long id, String utilityNumber, String surveySurveyor, String surveyStatus, Date createdDate, Date modifiedDate, Date surveyDate, String emirateName, String emirateRegione, String substationNumber, String vendorName, String premiseId, String premiseType, String premiseBuildingName, String address, String additionalNotes, BigDecimal latitude, BigDecimal longitude, String nbiotCNSRSPR, String nbiotCNSSINR, String nbiotSignalStrength, String nbiotServingIdSite, String nbiotServingIdCell, Boolean nbiotCNSRSPRAcceptableRange, Boolean nbiotCNSSINRAcceptableRange, Boolean nbiotSignalStrengthRange, Boolean crWMRequirement_01, Boolean crWMRequirement_02, Boolean crWMRequirement_03, Boolean crWMRequirement_04, Boolean crWMRequirement_05, Boolean crWMRequirement_06, Boolean crWMRequirement_07, Boolean crWMRequirement_08, Boolean crEMRequirement_01, Boolean crEMRequirement_02, Boolean crEMRequirement_03, Boolean crEMRequirement_04, Boolean crEMRequirement_05, Boolean crEMRequirement_06, Boolean crEMRequirement_07, Boolean crEMRequirement_08, Boolean psWMRequirement_01, Boolean psWMRequirement_02, Boolean psWMRequirement_03, Boolean psWMRequirement_04, Boolean psWMRequirement_05, Boolean psEMRequirement_01, Boolean psEMRequirement_02, Boolean psEMRequirement_03, Boolean psEMRequirement_04, Boolean psEMRequirement_05, Integer metersWM_AMI, Integer metersWM_NAMI, Integer metersWM_MBUS, Integer metersWM_WMBUS, Integer metersWM_Others, String metersWM_Lable_Others, String metersEM_Lable_Others, Integer metersEM_AMI, Integer metersEM_NAMI, Integer metersEM_DLMS, Integer metersEM_Euridis, Integer metersEM_Others, Long bomId, String customerCode, String contactPrimary, String contactAdditional, String cableLengthForEMeters, String gatewaysTypeForEMeters, Integer gatewaysNumberForEMeters, Integer metersElectricPerGateway, String cableLengthForWMeters, String gatewaysTypeForWMeters, Integer gatewaysNumberForWMeters, Integer metersWaterPerGateway, String surveyRejectReason, String surveyEntitledAction) {
        this.id = id;
        this.utilityNumber = utilityNumber;
        this.surveySurveyor = surveySurveyor;
        this.surveyStatus = surveyStatus;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.surveyDate = surveyDate;
        this.emirateName = emirateName;
        this.emirateRegione = emirateRegione;
        this.substationNumber = substationNumber;
        this.vendorName = vendorName;
        this.premiseId = premiseId;
        this.premiseType = premiseType;
        this.premiseBuildingName = premiseBuildingName;
        this.address = address;
        this.additionalNotes = additionalNotes;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nbiotCNSRSPR = nbiotCNSRSPR;
        this.nbiotCNSSINR = nbiotCNSSINR;
        this.nbiotSignalStrength = nbiotSignalStrength;
        this.nbiotServingIdSite = nbiotServingIdSite;
        this.nbiotServingIdCell = nbiotServingIdCell;
        this.nbiotCNSRSPRAcceptableRange = nbiotCNSRSPRAcceptableRange;
        this.nbiotCNSSINRAcceptableRange = nbiotCNSSINRAcceptableRange;
        this.nbiotSignalStrengthRange = nbiotSignalStrengthRange;
        this.crWMRequirement_01 = crWMRequirement_01;
        this.crWMRequirement_02 = crWMRequirement_02;
        this.crWMRequirement_03 = crWMRequirement_03;
        this.crWMRequirement_04 = crWMRequirement_04;
        this.crWMRequirement_05 = crWMRequirement_05;
        this.crWMRequirement_06 = crWMRequirement_06;
        this.crWMRequirement_07 = crWMRequirement_07;
        this.crWMRequirement_08 = crWMRequirement_08;
        this.crEMRequirement_01 = crEMRequirement_01;
        this.crEMRequirement_02 = crEMRequirement_02;
        this.crEMRequirement_03 = crEMRequirement_03;
        this.crEMRequirement_04 = crEMRequirement_04;
        this.crEMRequirement_05 = crEMRequirement_05;
        this.crEMRequirement_06 = crEMRequirement_06;
        this.crEMRequirement_07 = crEMRequirement_07;
        this.crEMRequirement_08 = crEMRequirement_08;
        this.psWMRequirement_01 = psWMRequirement_01;
        this.psWMRequirement_02 = psWMRequirement_02;
        this.psWMRequirement_03 = psWMRequirement_03;
        this.psWMRequirement_04 = psWMRequirement_04;
        this.psWMRequirement_05 = psWMRequirement_05;
        this.psEMRequirement_01 = psEMRequirement_01;
        this.psEMRequirement_02 = psEMRequirement_02;
        this.psEMRequirement_03 = psEMRequirement_03;
        this.psEMRequirement_04 = psEMRequirement_04;
        this.psEMRequirement_05 = psEMRequirement_05;
        this.metersWM_AMI = metersWM_AMI;
        this.metersWM_NAMI = metersWM_NAMI;
        this.metersWM_MBUS = metersWM_MBUS;
        this.metersWM_WMBUS = metersWM_WMBUS;
        this.metersWM_Others = metersWM_Others;
        this.metersWM_Lable_Others = metersWM_Lable_Others;
        this.metersEM_Lable_Others = metersEM_Lable_Others;
        this.metersEM_AMI = metersEM_AMI;
        this.metersEM_NAMI = metersEM_NAMI;
        this.metersEM_DLMS = metersEM_DLMS;
        this.metersEM_Euridis = metersEM_Euridis;
        this.metersEM_Others = metersEM_Others;
        this.bomId = bomId;
        this.customerCode = customerCode;
        this.contactPrimary = contactPrimary;
        this.contactAdditional = contactAdditional;
        this.cableLengthForEMeters = cableLengthForEMeters;
        this.gatewaysTypeForEMeters = gatewaysTypeForEMeters;
        this.gatewaysNumberForEMeters = gatewaysNumberForEMeters;
        this.metersElectricPerGateway = metersElectricPerGateway;
        this.cableLengthForWMeters = cableLengthForWMeters;
        this.gatewaysTypeForWMeters = gatewaysTypeForWMeters;
        this.gatewaysNumberForWMeters = gatewaysNumberForWMeters;
        this.metersWaterPerGateway = metersWaterPerGateway;
        this.surveyRejectReason = surveyRejectReason;
        this.surveyEntitledAction = surveyEntitledAction;
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

    public Boolean getNbiotCNSRSPRAcceptableRange() {
        return nbiotCNSRSPRAcceptableRange;
    }

    public Boolean getNbiotCNSSINRAcceptableRange() {
        return nbiotCNSSINRAcceptableRange;
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

    public Boolean getCrEMRequirement_06() {
        return crEMRequirement_06;
    }

    public Boolean getCrEMRequirement_07() {
        return crEMRequirement_07;
    }

    public Boolean getCrEMRequirement_08() {
        return crEMRequirement_08;
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

    public String getNbiotSignalStrength() {
        return nbiotSignalStrength;
    }

    public String getNbiotServingIdSite() {
        return nbiotServingIdSite;
    }

    public String getNbiotServingIdCell() {
        return nbiotServingIdCell;
    }

    public Boolean getNbiotSignalStrengthRange() {
        return nbiotSignalStrengthRange;
    }

    public String getCableLengthForEMeters() {
        if(cableLengthForEMeters == null)
        {
            cableLengthForEMeters = "";
        }
        return cableLengthForEMeters;
    }

    public String getGatewaysTypeForEMeters() {
        if(gatewaysTypeForEMeters == null)
        {
            gatewaysTypeForEMeters = "";
        }
        return gatewaysTypeForEMeters;
    }

    public Integer getGatewaysNumberForEMeters() {
        if(gatewaysNumberForEMeters == null)
        {
            gatewaysNumberForEMeters = 0;
        }
        return gatewaysNumberForEMeters;
    }

    public Integer getMetersElectricPerGateway() {
        if(metersElectricPerGateway == null)
        {
            metersElectricPerGateway = 0;
        }
        return metersElectricPerGateway;
    }

    public String getCableLengthForWMeters() {
        if(cableLengthForWMeters == null)
        {
            cableLengthForWMeters = "";
        }
        return cableLengthForWMeters;
    }

    public String getGatewaysTypeForWMeters() {
        if(gatewaysTypeForWMeters == null)
        {
            gatewaysTypeForWMeters = "";
        }
        return gatewaysTypeForWMeters;
    }

    public Integer getGatewaysNumberForWMeters() {
        if(gatewaysNumberForWMeters == null)
        {
            gatewaysNumberForWMeters = 0;
        }
        return gatewaysNumberForWMeters;
    }

    public Integer getMetersWaterPerGateway() {
        if(metersWaterPerGateway == null)
        {
            metersWaterPerGateway = 0;
        }
        return metersWaterPerGateway;
    }

    public String getSurveyRejectReason() {
        if(surveyRejectReason == null)
        {
            surveyRejectReason = "";
        }
        return surveyRejectReason;
    }

    public String getSurveyEntitledAction() {
        if(surveyEntitledAction == null)
        {
            surveyEntitledAction = "";
        }
        return surveyEntitledAction;
    }
    
    
    
    /*
    *
    *
    */
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
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

    public void setNbiotCNSRSPRAcceptableRange(Boolean nbiotCNSRSPRAcceptableRange) {
        this.nbiotCNSRSPRAcceptableRange = nbiotCNSRSPRAcceptableRange;
    }

    public void setNbiotCNSSINRAcceptableRange(Boolean nbiotCNSSINRAcceptableRange) {
        this.nbiotCNSSINRAcceptableRange = nbiotCNSSINRAcceptableRange;
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

    public void setNbiotSignalStrength(String nbiotSignalStrength) {
        this.nbiotSignalStrength = nbiotSignalStrength;
    }

    public void setNbiotServingIdSite(String nbiotServingIdSite) {
        this.nbiotServingIdSite = nbiotServingIdSite;
    }

    public void setNbiotServingIdCell(String nbiotServingIdCell) {
        this.nbiotServingIdCell = nbiotServingIdCell;
    }

    public void setNbiotSignalStrengthRange(Boolean nbiotSignalStrengthRange) {
        this.nbiotSignalStrengthRange = nbiotSignalStrengthRange;
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
    
    
    
    
    
    
}
