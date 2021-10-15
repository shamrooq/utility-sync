/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.controllers;

import ae.etisalatdigital.commonUtils.exception.BusinessException;
import ae.etisalatdigital.iot.ops.utility.sync.beans.Estimation;
import ae.etisalatdigital.iot.ops.utility.sync.buses.BOMGatewayEstBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.SurveyBus;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.RequestDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.SurveyDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author appadmin
 */
@Named(value = "ussurveyController")
@SessionScoped
public class SurveyController implements Serializable  {
    
    private static final Logger LOGGER = Logger.getLogger(SurveyController.class);
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    private SurveyDTO selectedSurvey;
    private String utilityNumber;
    private Long surveyID;
    
    
    private boolean surveyCompleted;
    private String surveyEntitledAction;
    
    private boolean crWMRequirement_01;
    
    private boolean crWMRequirement_02;
    
    private boolean crWMRequirement_03;
    
    private boolean crWMRequirement_04;
    
    private boolean crWMRequirement_05;
    
    private boolean crWMRequirement_06;
    
    private boolean crWMRequirement_07;
    
    private boolean crWMRequirement_08;
    
    private boolean crEMRequirement_01;
    
    private boolean crEMRequirement_02;
    
    private boolean crEMRequirement_03;
    
    private boolean crEMRequirement_04;
    
    private boolean crEMRequirement_05;
    
    private boolean crEMRequirement_06;
    
    private boolean crEMRequirement_07;
    
    private boolean crEMRequirement_08;
    
    
    private boolean psWMRequirement_01;
    
    private boolean psWMRequirement_02;
    
    private boolean psWMRequirement_03;
    
    private boolean psWMRequirement_04;
    
    private boolean psWMRequirement_05;
    
    private boolean psWMRequirement_06;
    
    
    
    private boolean psEMRequirement_01;
    
    private boolean psEMRequirement_02;
    
    private boolean psEMRequirement_03;
    
    private boolean psEMRequirement_04;
    
    private boolean psEMRequirement_05;
    
    
    
    private Integer metersWM_AMI;
    
    private Integer metersWM_NAMI;
    
    private Integer metersWM_MBUS;
    
    private Integer metersWM_WMBUS;
    
    private Integer metersWM_Others;
    
    
    private Integer metersEM_AMI;
    
    private Integer metersEM_NAMI;
    
    private Integer metersEM_DLMS;
    
    private Integer metersEM_Euridis;
    
    private Integer metersEM_Others;
    private String metersEM_Label_Others;
    private String metersWM_Label_Others;
    
    private String nbiotCNSRSPR;
    private String nbiotCNSSINR;
    private String nbiotSignalStrength;
    private String nbiotServingIdSite;
    private String nbiotServingIdCell;
    
    private boolean nbiotCNSRSPRAcceptableRange;
    private boolean nbiotCNSSINRAcceptableRange;
    private boolean nbiotSignalStrengthRange; 
    
    private String surveyorName;
    private String additionalNotes;
    private Date surveyDate;
    private String entitledAction;
    private String rejectionRemarks; 
    
    private String cableLengthForEMeters;
    private String gatewaysTypeForEMeters;
    private Integer gatewaysNumberForEMeters;
    private Integer metersElectricPerGateway;
    
    private String cableLengthForWMeters;
    private String gatewaysTypeForWMeters;
    private Integer gatewaysNumberForWMeters;
    private Integer metersWaterPerGateway;
    
    
    
    
    
    @Inject
    private SurveyBus surveyBus;
    
    
    
    public static Logger getLOGGER() {
        return LOGGER;
    }

    public SurveyDTO getSelectedSurvey() {
        return selectedSurvey;
    }

    public String getUtilityNumber() {
        return utilityNumber;
    }

    public Long getSurveyID() {
        return surveyID;
    }
    
    

    public void setSelectedSurvey(SurveyDTO selectedSurvey) {
        this.selectedSurvey = selectedSurvey;
    }

    public void setUtilityNumber(String utilityNumber) {
        this.utilityNumber = utilityNumber;
    }

    public void setSurveyID(Long surveyID) {
        this.surveyID = surveyID;
    }
    
    
    
    
    
    public void fetchSurveyTemplate(String utilityNumber){
        selectedSurvey = surveyBus.findAllByUN(utilityNumber);
        if(selectedSurvey != null){
            surveyID = selectedSurvey.getId();
        }
    }
    
    public void saveGPSInplace(){
        
    }
    
    public void initiateAll(){
        
    }
    
    public void refresh(){
        selectedSurvey = null;
    }
    
    public void fetchSelected(String utilityNumber) throws BusinessException {
        if (selectedSurvey != null || utilityNumber == null) {
            return;
        }
         
        selectedSurvey = surveyBus.findAllByUN(utilityNumber);
        if(selectedSurvey != null){
            surveyID = selectedSurvey.getId();
            metersWM_AMI = selectedSurvey.getMetersWM_AMI();
            metersWM_NAMI = selectedSurvey.getMetersWM_NAMI();
            metersWM_MBUS = selectedSurvey.getMetersWM_MBUS();
            metersWM_WMBUS = selectedSurvey.getMetersWM_WMBUS();
            metersWM_Others = selectedSurvey.getMetersWM_Others();
            metersWM_Label_Others = selectedSurvey.getMetersWM_Lable_Others();
            
            metersEM_AMI = selectedSurvey.getMetersEM_AMI();
            metersEM_NAMI = selectedSurvey.getMetersEM_NAMI();
            metersEM_DLMS = selectedSurvey.getMetersEM_DLMS();
            metersEM_Euridis = selectedSurvey.getMetersEM_Euridis();
            metersEM_Others = selectedSurvey.getMetersEM_Others();
            metersEM_Label_Others = selectedSurvey.getMetersEM_Lable_Others();
            
            crWMRequirement_01 = selectedSurvey.getCrWMRequirement_01();
            crWMRequirement_02 = selectedSurvey.getCrWMRequirement_02();
            crWMRequirement_03 = selectedSurvey.getCrWMRequirement_03();
            crWMRequirement_04 = selectedSurvey.getCrWMRequirement_04();
            crWMRequirement_05 = selectedSurvey.getCrWMRequirement_05();
            crWMRequirement_06 = selectedSurvey.getCrWMRequirement_06();
            crWMRequirement_07 = selectedSurvey.getCrWMRequirement_07();
            crWMRequirement_08 = selectedSurvey.getCrWMRequirement_08();
            
            crEMRequirement_01 = selectedSurvey.getCrEMRequirement_01();
            crEMRequirement_02 = selectedSurvey.getCrEMRequirement_02();
            crEMRequirement_03 = selectedSurvey.getCrEMRequirement_03();
            crEMRequirement_04 = selectedSurvey.getCrEMRequirement_04();
            crEMRequirement_05 = selectedSurvey.getCrEMRequirement_05();
            crEMRequirement_06 = selectedSurvey.getCrEMRequirement_06();
            crEMRequirement_07 = selectedSurvey.getCrEMRequirement_07();
            crEMRequirement_08 = selectedSurvey.getCrEMRequirement_08();
            
            psWMRequirement_01 = selectedSurvey.getPsWMRequirement_01();
            psWMRequirement_02 = selectedSurvey.getPsWMRequirement_02();
            psWMRequirement_03 = selectedSurvey.getPsWMRequirement_03();
            psWMRequirement_04 = selectedSurvey.getPsWMRequirement_04();
            psWMRequirement_05 = selectedSurvey.getPsWMRequirement_05();
            
            psEMRequirement_01 = selectedSurvey.getPsEMRequirement_01();
            psEMRequirement_02 = selectedSurvey.getPsEMRequirement_02();
            psEMRequirement_03 = selectedSurvey.getPsEMRequirement_03();
            psEMRequirement_04 = selectedSurvey.getPsEMRequirement_04();
            psEMRequirement_05 = selectedSurvey.getPsEMRequirement_05();
            
            nbiotCNSRSPR = selectedSurvey.getNbiotCNSRSPR();
            nbiotCNSSINR = selectedSurvey.getNbiotCNSSINR();
            nbiotSignalStrength = selectedSurvey.getNbiotSignalStrength();
            nbiotServingIdSite = selectedSurvey.getNbiotServingIdSite();
            nbiotServingIdCell = selectedSurvey.getNbiotServingIdCell();

            nbiotCNSRSPRAcceptableRange = selectedSurvey.getNbiotCNSRSPRAcceptableRange();
            nbiotCNSSINRAcceptableRange = selectedSurvey.getNbiotCNSSINRAcceptableRange();
            nbiotSignalStrengthRange = selectedSurvey.getNbiotSignalStrengthRange(); 
            
            surveyorName =  selectedSurvey.getSurveySurveyor();
            
            surveyDate = selectedSurvey.getSurveyDate();
            if(surveyDate == null){
                surveyDate = DateTime.now().toDate();
            }
            additionalNotes = selectedSurvey.getAdditionalNotes();
            entitledAction = selectedSurvey.getSurveyEntitledAction();
            rejectionRemarks = selectedSurvey.getSurveyRejectReason();
            if(selectedSurvey.getSurveyStatus().equalsIgnoreCase("Completed")){
                this.surveyCompleted = true;
            }else if (selectedSurvey.getSurveyStatus().equalsIgnoreCase("Failed")){
                this.surveyCompleted = false;
            }
        }
    }
    
    
    
    
    
    private void updateSelected() throws BusinessException {
        
               selectedSurvey.setMetersWM_AMI(metersWM_AMI);
               selectedSurvey.setMetersWM_NAMI(metersWM_NAMI);
               selectedSurvey.setMetersWM_MBUS(metersWM_MBUS);
               selectedSurvey.setMetersWM_WMBUS(metersWM_WMBUS);
               selectedSurvey.setMetersWM_Others(metersWM_Others);
               selectedSurvey.setMetersWM_Lable_Others(metersWM_Label_Others);
            
               selectedSurvey.setMetersEM_AMI(metersEM_AMI);
               selectedSurvey.setMetersEM_NAMI(metersEM_NAMI);
               selectedSurvey.setMetersEM_DLMS(metersEM_DLMS);
               selectedSurvey.setMetersEM_Euridis(metersEM_Euridis);
               selectedSurvey.setMetersEM_Others(metersEM_Others);
               selectedSurvey.setMetersEM_Lable_Others(metersEM_Label_Others);
            
               selectedSurvey.setCrWMRequirement_01(crWMRequirement_01);
               selectedSurvey.setCrWMRequirement_02(crWMRequirement_01);
               selectedSurvey.setCrWMRequirement_03(crWMRequirement_02);
               selectedSurvey.setCrWMRequirement_04(crWMRequirement_03);
               selectedSurvey.setCrWMRequirement_05(crWMRequirement_04);
               selectedSurvey.setCrWMRequirement_06(crWMRequirement_05);
               selectedSurvey.setCrWMRequirement_07(crWMRequirement_06);
               selectedSurvey.setCrWMRequirement_08(crWMRequirement_07);
            
               selectedSurvey.setCrEMRequirement_01(crEMRequirement_01);
               selectedSurvey.setCrEMRequirement_02(crEMRequirement_02);
               selectedSurvey.setCrEMRequirement_03(crEMRequirement_03);
               selectedSurvey.setCrEMRequirement_04(crEMRequirement_04);
               selectedSurvey.setCrEMRequirement_05(crEMRequirement_05);
               selectedSurvey.setCrEMRequirement_06(crEMRequirement_06);
               selectedSurvey.setCrEMRequirement_07(crEMRequirement_07);
               selectedSurvey.setCrEMRequirement_08(crEMRequirement_08);
            
               selectedSurvey.setPsWMRequirement_01(psWMRequirement_01);
               selectedSurvey.setPsWMRequirement_02(psWMRequirement_02);
               selectedSurvey.setPsWMRequirement_03(psWMRequirement_03);
               selectedSurvey.setPsWMRequirement_04(psWMRequirement_04);
               selectedSurvey.setPsWMRequirement_05(psWMRequirement_05);
            
               selectedSurvey.setPsEMRequirement_01(psEMRequirement_01);
               selectedSurvey.setPsEMRequirement_02(psEMRequirement_02);
               selectedSurvey.setPsEMRequirement_03(psEMRequirement_03);
               selectedSurvey.setPsEMRequirement_04(psEMRequirement_04);
               selectedSurvey.setPsEMRequirement_05(psEMRequirement_05);
            
                 selectedSurvey.setNbiotCNSRSPR(nbiotCNSRSPR);
                 selectedSurvey.setNbiotCNSSINR(nbiotCNSSINR);
                 selectedSurvey.setNbiotSignalStrength(nbiotSignalStrength);
                 selectedSurvey.setNbiotServingIdSite(nbiotServingIdSite);
                 selectedSurvey.setNbiotServingIdCell(nbiotServingIdCell);

                 selectedSurvey.setNbiotCNSRSPRAcceptableRange(nbiotCNSRSPRAcceptableRange);
                 selectedSurvey.setNbiotCNSSINRAcceptableRange(nbiotCNSSINRAcceptableRange);
                 selectedSurvey.setNbiotSignalStrengthRange(nbiotSignalStrengthRange); 
            
                selectedSurvey.setSurveySurveyor(surveyorName);
                //selectedSurvey.setCreatedDate(surveyDate);
                selectedSurvey.setAdditionalNotes(additionalNotes);
                //selectedSurvey.setSurveyStatus(entitledAction);                
                //selectedSurvey.setSurveyEntitledAction(entitledAction);
                selectedSurvey.setSurveyRejectReason(rejectionRemarks);
    }
    
    
    
    public boolean saveSurveyDetails(RequestDTO selectedRequest, String submissionType){
        SurveyDTO savable = new SurveyDTO();
        try{
               savable.setId(surveyID);
               savable.setUtilityNumber(selectedRequest.getId());
               savable.setSurveyDate(surveyDate);
               
               savable.setEmirateName(selectedRequest.getEmirateName());
               savable.setEmirateRegione(selectedRequest.getEmirateRegionName());
               savable.setLatitude(selectedRequest.getPremiseLatitude());
               savable.setLongitude(selectedRequest.getPremiseLongitude());
               savable.setBomId(selectedRequest.getActiveBom());
               
               
               savable.setMetersWM_AMI(metersWM_AMI);
               savable.setMetersWM_NAMI(metersWM_NAMI);
               savable.setMetersWM_MBUS(metersWM_MBUS);
               savable.setMetersWM_WMBUS(metersWM_WMBUS);
               savable.setMetersWM_Others(metersWM_Others);
               savable.setMetersWM_Lable_Others(metersWM_Label_Others);
            
               savable.setMetersEM_AMI(metersEM_AMI);
               savable.setMetersEM_NAMI(metersEM_NAMI);
               savable.setMetersEM_DLMS(metersEM_DLMS);
               savable.setMetersEM_Euridis(metersEM_Euridis);
               savable.setMetersEM_Others(metersEM_Others);
               savable.setMetersEM_Lable_Others(metersEM_Label_Others);
            
               savable.setCrWMRequirement_01(crWMRequirement_01);
               savable.setCrWMRequirement_02(crWMRequirement_01);
               savable.setCrWMRequirement_03(crWMRequirement_02);
               savable.setCrWMRequirement_04(crWMRequirement_03);
               savable.setCrWMRequirement_05(crWMRequirement_04);
               savable.setCrWMRequirement_06(crWMRequirement_05);
               savable.setCrWMRequirement_07(crWMRequirement_06);
               savable.setCrWMRequirement_08(crWMRequirement_07);
            
               savable.setCrEMRequirement_01(crEMRequirement_01);
               savable.setCrEMRequirement_02(crEMRequirement_02);
               savable.setCrEMRequirement_03(crEMRequirement_03);
               savable.setCrEMRequirement_04(crEMRequirement_04);
               savable.setCrEMRequirement_05(crEMRequirement_05);
               savable.setCrEMRequirement_06(crEMRequirement_06);
               savable.setCrEMRequirement_07(crEMRequirement_07);
               savable.setCrEMRequirement_08(crEMRequirement_08);
            
               savable.setPsWMRequirement_01(psWMRequirement_01);
               savable.setPsWMRequirement_02(psWMRequirement_02);
               savable.setPsWMRequirement_03(psWMRequirement_03);
               savable.setPsWMRequirement_04(psWMRequirement_04);
               savable.setPsWMRequirement_05(psWMRequirement_05);
            
               savable.setPsEMRequirement_01(psEMRequirement_01);
               savable.setPsEMRequirement_02(psEMRequirement_02);
               savable.setPsEMRequirement_03(psEMRequirement_03);
               savable.setPsEMRequirement_04(psEMRequirement_04);
               savable.setPsEMRequirement_05(psEMRequirement_05);
            
               savable.setNbiotCNSRSPR(nbiotCNSRSPR);
               savable.setNbiotCNSSINR(nbiotCNSSINR);
               savable.setNbiotSignalStrength(nbiotSignalStrength);
               savable.setNbiotServingIdSite(nbiotServingIdSite);
               savable.setNbiotServingIdCell(nbiotServingIdCell);

               savable.setNbiotCNSRSPRAcceptableRange(nbiotCNSRSPRAcceptableRange);
               savable.setNbiotCNSSINRAcceptableRange(nbiotCNSSINRAcceptableRange);
               savable.setNbiotSignalStrengthRange(nbiotSignalStrengthRange); 
            
               savable.setSurveySurveyor(surveyorName);
               savable.setCreatedDate(surveyDate);
               savable.setAdditionalNotes(additionalNotes);
               
               
               savable.setSurveyStatus(submissionType);
               savable.setSurveyRejectReason(rejectionRemarks); 
               
                                     
               
               /*
               Estimation estElectricity = estimation.get(0);
               Estimation estWater = estimation.get(1);
               savable.setCableLengthForEMeters(estElectricity.getEstimatedCableLength());
               savable.setCableLengthForWMeters(estWater.getEstimatedCableLength());
            
               savable.setGatewaysTypeForEMeters(estElectricity.getGatewaysTypeProposed());
               savable.setGatewaysTypeForWMeters(estWater.getGatewaysTypeProposed());
            
               savable.setGatewaysNumberForEMeters(estElectricity.getGatewaysRequired());
               savable.setGatewaysNumberForWMeters(estWater.getGatewaysRequired());
            
               savable.setMetersElectricPerGateway(Integer.parseInt(estElectricity.getMetersTotal()));
               savable.setMetersWaterPerGateway(Integer.parseInt(estWater.getMetersTotal()));
               */
               surveyBus.updateSurveyDetails(savable);
            
        }catch(Exception exc){
            System.out.println("SurveyController.saveSurveyDetails()-"+exc.getMessage());
            return false;
        }
        
        
        
        
        return true;
    }
    
    /**
     * 
     * @param event
     * @return 
     */
    
    public String onFlowProcess(FlowEvent event) {
        
        return event.getNewStep();
        
    }

    public EntityManager getEm() {
        return em;
    }

    public String getSurveyEntitledAction() {
        return surveyEntitledAction;
    }

    public boolean getCrWMRequirement_01() {
        return crWMRequirement_01;
    }

    public boolean getCrWMRequirement_02() {
        return crWMRequirement_02;
    }

    public boolean getCrWMRequirement_03() {
        return crWMRequirement_03;
    }

    public boolean getCrWMRequirement_04() {
        return crWMRequirement_04;
    }

    public boolean getCrWMRequirement_05() {
        return crWMRequirement_05;
    }

    public boolean getCrWMRequirement_06() {
        return crWMRequirement_06;
    }

    public boolean getCrWMRequirement_07() {
        return crWMRequirement_07;
    }

    public boolean getCrWMRequirement_08() {
        return crWMRequirement_08;
    }
    
    

    public boolean getCrEMRequirement_01() {
        return crEMRequirement_01;
    }

    public boolean getCrEMRequirement_02() {
        return crEMRequirement_02;
    }

    public boolean getCrEMRequirement_03() {
        return crEMRequirement_03;
    }

    public boolean getCrEMRequirement_04() {
        return crEMRequirement_04;
    }

    public boolean getCrEMRequirement_05() {
        return crEMRequirement_05;
    }

    public boolean getCrEMRequirement_06() {
        return crEMRequirement_06;
    }

    public boolean getCrEMRequirement_07() {
        return crEMRequirement_07;
    }

    public boolean getCrEMRequirement_08() {
        return crEMRequirement_08;
    }

    public boolean getPsWMRequirement_01() {
        return psWMRequirement_01;
    }

    public boolean getPsWMRequirement_02() {
        return psWMRequirement_02;
    }

    public boolean getPsWMRequirement_03() {
        return psWMRequirement_03;
    }

    public boolean getPsWMRequirement_04() {
        return psWMRequirement_04;
    }

    public boolean getPsWMRequirement_05() {
        return psWMRequirement_05;
    }

    public boolean getPsWMRequirement_06() {
        return psWMRequirement_06;
    }

    public boolean getPsEMRequirement_01() {
        return psEMRequirement_01;
    }

    public boolean getPsEMRequirement_02() {
        return psEMRequirement_02;
    }

    public boolean getPsEMRequirement_03() {
        return psEMRequirement_03;
    }

    public boolean getPsEMRequirement_04() {
        return psEMRequirement_04;
    }

    public boolean getPsEMRequirement_05() {
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

    public String getMetersEM_Label_Others() {
        return metersEM_Label_Others;
    }

    public String getMetersWM_Label_Others() {
        return metersWM_Label_Others;
    }

    public String getSurveyorName() {
        return surveyorName;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public SurveyBus getSurveyBus() {
        return surveyBus;
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

    public boolean getNbiotCNSRSPRAcceptableRange() {
        return nbiotCNSRSPRAcceptableRange;
    }

    public boolean getNbiotCNSSINRAcceptableRange() {
        return nbiotCNSSINRAcceptableRange;
    }

    public boolean getNbiotSignalStrengthRange() {
        return nbiotSignalStrengthRange;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }

    public String getEntitledAction() {
        return entitledAction;
    }

    public String getRejectionRemarks() {
        return rejectionRemarks;
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

    

    

    public boolean isSurveyCompleted() {
        return surveyCompleted;
    }
    
    
    
    /**
     * 
     * @param em 
     */
    
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void setSurveyEntitledAction(String surveyEntitledAction) {
        this.surveyEntitledAction = surveyEntitledAction;
    }

    public void setCrWMRequirement_01(boolean crWMRequirement_01) {
        this.crWMRequirement_01 = crWMRequirement_01;
    }

    public void setCrWMRequirement_02(boolean crWMRequirement_02) {
        this.crWMRequirement_02 = crWMRequirement_02;
    }

    public void setCrWMRequirement_03(boolean crWMRequirement_03) {
        this.crWMRequirement_03 = crWMRequirement_03;
    }

    public void setCrWMRequirement_04(boolean crWMRequirement_04) {
        this.crWMRequirement_04 = crWMRequirement_04;
    }

    public void setCrWMRequirement_05(boolean crWMRequirement_05) {
        this.crWMRequirement_05 = crWMRequirement_05;
    }

    public void setCrWMRequirement_06(boolean crWMRequirement_06) {
        this.crWMRequirement_06 = crWMRequirement_06;
    }

    public void setCrWMRequirement_07(boolean crWMRequirement_07) {
        this.crWMRequirement_07 = crWMRequirement_07;
    }

    public void setCrWMRequirement_08(boolean crWMRequirement_08) {
        this.crWMRequirement_08 = crWMRequirement_08;
    }
    
    

    public void setCrEMRequirement_01(boolean crEMRequirement_01) {
        this.crEMRequirement_01 = crEMRequirement_01;
    }

    public void setCrEMRequirement_02(boolean crEMRequirement_02) {
        this.crEMRequirement_02 = crEMRequirement_02;
    }

    public void setCrEMRequirement_03(boolean crEMRequirement_03) {
        this.crEMRequirement_03 = crEMRequirement_03;
    }

    public void setCrEMRequirement_04(boolean crEMRequirement_04) {
        this.crEMRequirement_04 = crEMRequirement_04;
    }

    public void setCrEMRequirement_05(boolean crEMRequirement_05) {
        this.crEMRequirement_05 = crEMRequirement_05;
    }

    public void setCrEMRequirement_06(boolean crEMRequirement_06) {
        this.crEMRequirement_06 = crEMRequirement_06;
    }

    public void setCrEMRequirement_07(boolean crEMRequirement_07) {
        this.crEMRequirement_07 = crEMRequirement_07;
    }

    public void setCrEMRequirement_08(boolean crEMRequirement_08) {
        this.crEMRequirement_08 = crEMRequirement_08;
    }

    public void setPsWMRequirement_01(boolean psWMRequirement_01) {
        this.psWMRequirement_01 = psWMRequirement_01;
    }

    public void setPsWMRequirement_02(boolean psWMRequirement_02) {
        this.psWMRequirement_02 = psWMRequirement_02;
    }

    public void setPsWMRequirement_03(boolean psWMRequirement_03) {
        this.psWMRequirement_03 = psWMRequirement_03;
    }

    public void setPsWMRequirement_04(boolean psWMRequirement_04) {
        this.psWMRequirement_04 = psWMRequirement_04;
    }

    public void setPsWMRequirement_05(boolean psWMRequirement_05) {
        this.psWMRequirement_05 = psWMRequirement_05;
    }

    public void setPsWMRequirement_06(boolean psWMRequirement_06) {
        this.psWMRequirement_06 = psWMRequirement_06;
    }

    public void setPsEMRequirement_01(boolean psEMRequirement_01) {
        this.psEMRequirement_01 = psEMRequirement_01;
    }

    public void setPsEMRequirement_02(boolean psEMRequirement_02) {
        this.psEMRequirement_02 = psEMRequirement_02;
    }

    public void setPsEMRequirement_03(boolean psEMRequirement_03) {
        this.psEMRequirement_03 = psEMRequirement_03;
    }

    public void setPsEMRequirement_04(boolean psEMRequirement_04) {
        this.psEMRequirement_04 = psEMRequirement_04;
    }

    public void setPsEMRequirement_05(boolean psEMRequirement_05) {
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

    public void setMetersEM_Label_Others(String metersEM_Label_Others) {
        this.metersEM_Label_Others = metersEM_Label_Others;
    }

    public void setMetersWM_Label_Others(String metersWM_Label_Others) {
        this.metersWM_Label_Others = metersWM_Label_Others;
    }

    public void setSurveyorName(String surveyorName) {
        this.surveyorName = surveyorName;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public void setSurveyBus(SurveyBus surveyBus) {
        this.surveyBus = surveyBus;
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

    public void setNbiotCNSRSPRAcceptableRange(boolean nbiotCNSRSPRAcceptableRange) {
        this.nbiotCNSRSPRAcceptableRange = nbiotCNSRSPRAcceptableRange;
    }

    public void setNbiotCNSSINRAcceptableRange(boolean nbiotCNSSINRAcceptableRange) {
        this.nbiotCNSSINRAcceptableRange = nbiotCNSSINRAcceptableRange;
    }

    public void setNbiotSignalStrengthRange(boolean nbiotSignalStrengthRange) {
        this.nbiotSignalStrengthRange = nbiotSignalStrengthRange;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    public void setEntitledAction(String entitledAction) {
        this.entitledAction = entitledAction;
    }

    public void setRejectionRemarks(String rejectionRemarks) {
        this.rejectionRemarks = rejectionRemarks;
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

    

   

    public void setSurveyCompleted(boolean surveyCompleted) {
        if(surveyCompleted){
            setSurveyEntitledAction("Survey Completed");
            
        }else{
            setSurveyEntitledAction("Survey Failed");
        }
        this.surveyCompleted = surveyCompleted;
    }
    
    
    
    
}
