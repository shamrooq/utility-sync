/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.controllers;
import ae.etisalatdigital.commonUtils.exception.BusinessException;
import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.commonUtils.exception.WebException;
import ae.etisalatdigital.iot.ops.utility.sync.beans.Estimation;
import ae.etisalatdigital.iot.ops.utility.sync.beans.installation.InstallationSemanticView;
import ae.etisalatdigital.iot.ops.utility.sync.beans.installation.UtilityGatewayMeterSemantics;
import ae.etisalatdigital.iot.ops.utility.sync.buses.BOMMeterBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTBusinessBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTEmirateBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTEmirateRegionBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTPremiseTypeBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTVendorBus;

import ae.etisalatdigital.iot.ops.utility.sync.dtos.SurveyDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import ae.etisalatdigital.iot.ops.utility.sync.buses.SurveyBus;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTBusiness;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTEmirateRegions;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTEmirates;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTPremiseTypes;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTProtocol;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTVendors;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Requests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author au_mobility
 */

@Named
@ViewScoped
public class InstallationController implements Serializable  {
    
    private static final Logger LOGGER = Logger.getLogger(PreconfigurationViewController.class);
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    
   
    private String utilityNumber;
    private String errorMessage;
    private String surveyorSignature;
    private SurveyDTO surveyByUtilityNumber;
    
    private Requests selectedRequest;
    
    private String emirateName;
    private String emirateRegion;
    private String substationNumber;
    private String premiseBuildingName;
    private String premiseId;
    private String premiseType;
    private String plotNumber;
    private String longitude;
    private String latitude;
    private String address;
    
    private String vendorName;
    
    
    private Boolean crWMRequirement_01;
    
    private Boolean crWMRequirement_02;
    
    private Boolean crWMRequirement_03;
    
    private Boolean crWMRequirement_04;
    
    private Boolean crWMRequirement_05;
    
    private Boolean crWMRequirement_06;
    
    private Boolean crEMRequirement_01;
    
    private Boolean crEMRequirement_02;
    
    private Boolean crEMRequirement_03;
    
    private Boolean crEMRequirement_04;
    
    private Boolean crEMRequirement_05;
    
    private Boolean crEMRequirement_06;
    
    
    
    private Boolean psWMRequirement_01;
    
    private Boolean psWMRequirement_02;
    
    private Boolean psWMRequirement_03;
    
    private Boolean psWMRequirement_04;
    
    private Boolean psWMRequirement_05;
    
    private Boolean psWMRequirement_06;
    
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
    
    
    private Integer metersEM_AMI;
    
    private Integer metersEM_NAMI;
    
    private Integer metersEM_DLMS;
    
    private Integer metersEM_Euridis;
    
    private Integer metersEM_Others;
    
    private String customerCode;
    private String contactPrimary;
    private String contactAdditional;
    
    private String surveyorName;
    private String additionalNotes;
    
    List<BOMMeterDTO> metersElectricity;
    List<BOMMeterDTO> metersWater;
    List<MSTVendors> vendors;
    List<MSTEmirates> emirates;
    List<MSTEmirateRegions> emirateRegions;
    List<MSTPremiseTypes> premiseTypes;
    List<MSTBusiness> businessTypes;
    List<MSTProtocol> protocols;
    
    
    
    List<Estimation> estimation;
    private InstallationSemanticView installationSemanticView;

    @Inject
    private SurveyBus surveyBus;
    
    @Inject
    private MSTVendorBus vendorBus;
    
    @Inject
    private MSTEmirateBus emirateBus;
    
    @Inject
    private MSTEmirateRegionBus emirateRegionBus;
    
    @Inject
    private MSTPremiseTypeBus premiseTypeBus;
    
    
    @Inject
    private MSTBusinessBus businessBus;
    
    @Inject
    private RequestController usrequestController;
    
    
    @Inject
    private BOMMeterBus bomMetersBus;
    
    @Inject
    private BOMGatewaysController gatewaysController;
    

    public static Logger getLOGGER() {
        return LOGGER;
    }
    
    public String onFlowProcess(FlowEvent event) throws WebException, DataAccessException, BusinessException {
        String errormsg;
        FacesMessage msg;
        try{
            if ("configureTab".equals(event.getNewStep())) {
                if ("Survey Completed".equalsIgnoreCase(selectedRequest.getRequestStatus())) {
                    msg = new FacesMessage("Thanks Survey was completed.");
                    msg.setSeverity(FacesMessage.SEVERITY_INFO);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    msg = new FacesMessage("Please complete Survey First.");
                    msg.setSeverity(FacesMessage.SEVERITY_FATAL);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    return event.getOldStep();
                }
            }
            else if("semanticsTab".equals(event.getNewStep())){
                fetchGatewayMetersSemantics(this.selectedRequest.getActiveBom());
            }
        }catch(Exception exc){
            LOGGER.error("Error in wizard flow process", exc);
            errormsg = "System Error, Contact System Administrator";
            //exc.printStackTrace();
            msg = new FacesMessage(errormsg);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return event.getOldStep();
        }
        
        return event.getNewStep();
    }

    public void updateSelected() {
        System.out.println("Updating selected ...");
        try {
            this.selectedRequest = em.find(Requests.class, this.utilityNumber);
        } catch (Exception e) {
            this.selectedRequest = null;
        }
        System.out.println("Updating selected [OK]");
    }
    
    public Requests getSelectedRequest() {
        return selectedRequest;
    }

    
    
    
    public RequestController getUsrequestController() {
        return usrequestController;
    }

    public BOMGatewaysController getGatewaysController() {
        return gatewaysController;
    }
    
    
    
    
    
    

    public List<MSTVendors> getVendors() {
        return vendors;
    }
    
    
    

    public EntityManager getEm() {
        return em;
    }

    public String getUtilityNumber() {
        return utilityNumber;
    }

    public SurveyBus getSurveyBus() {
        return surveyBus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<BOMMeterDTO> getMetersElectricity() {
        return metersElectricity;
    }

    public List<BOMMeterDTO> getMetersWater() {
        return metersWater;
    }

    public List<Estimation> getEstimation() {
        return estimation;
    }

    public List<MSTProtocol> getProtocols() {
        return protocols;
    }
    
    

    public SurveyDTO getSurveyByUtilityNumber() {
        return surveyByUtilityNumber;
    }

    public String getEmirateName() {
        return emirateName;
    }

    public String getEmirateRegion() {
        return emirateRegion;
    }

    public String getSubstationNumber() {
        return substationNumber;
    }

    public String getPremiseBuildingName() {
        return premiseBuildingName;
    }

    public String getPremiseId() {
        return premiseId;
    }

    public String getPremiseType() {
        return premiseType;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
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

    public Boolean getPsWMRequirement_06() {
        return psWMRequirement_06;
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

    public String getSurveyorSignature() {
        return surveyorSignature;
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

    public List<MSTBusiness> getBusinessTypes() {
        return businessTypes;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getSurveyorName() {
        return surveyorName;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }
    
    
    public String getPremiseLatitudeStr(){
        if(selectedRequest == null)
            return "0.0";
        return selectedRequest.getPremiseLatitude().toString();
    }
    
    public String getPremiseLongitudeStr(){
        if(selectedRequest == null)
            return "0.0";
        return selectedRequest.getPremiseLongitude().toString();
    }
    
    /*
    *
    *
    */
    
    public void setSelectedRequest(Requests selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    public void setGatewaysController(BOMGatewaysController gatewaysController) {
        this.gatewaysController = gatewaysController;
    }
    
    

    public void setUsrequestController(RequestController usrequestController) {
        this.usrequestController = usrequestController;
    }
    
    
    
    

    public void setVendors(List<MSTVendors> vendors) {
        this.vendors = vendors;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void setUtilityNumber(String utilityNumber) {
        this.utilityNumber = utilityNumber;
    }

    public void setSurveyBus(SurveyBus surveyBus) {
        this.surveyBus = surveyBus;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setMetersElectricity(List<BOMMeterDTO> metersElectricity) {
        this.metersElectricity = metersElectricity;
    }

    public void setMetersWater(List<BOMMeterDTO> metersWater) {
        this.metersWater = metersWater;
    }

    public void setEstimation(List<Estimation> estimation) {
        this.estimation = estimation;
    }

    public void setProtocols(List<MSTProtocol> protocols) {
        this.protocols = protocols;
    }
    
    

    public void setSurveyByUtilityNumber(SurveyDTO surveyByUtilityNumber) {
        this.surveyByUtilityNumber = surveyByUtilityNumber;
    }

    public void setEmirateName(String emirateName) {
        this.emirateName = emirateName;
    }

    public void setEmirateRegion(String emirateRegion) {
        this.emirateRegion = emirateRegion;
    }

    public void setSubstationNumber(String substationNumber) {
        this.substationNumber = substationNumber;
    }

    public void setPremiseBuildingName(String premiseBuildingName) {
        this.premiseBuildingName = premiseBuildingName;
    }

    public void setPremiseId(String premiseId) {
        this.premiseId = premiseId;
    }

    public void setPremiseType(String premiseType) {
        this.premiseType = premiseType;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setPsWMRequirement_06(Boolean psWMRequirement_06) {
        this.psWMRequirement_06 = psWMRequirement_06;
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

    public void setSurveyorSignature(String surveyorSignature) {
        this.surveyorSignature = surveyorSignature;
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

    public void setBusinessTypes(List<MSTBusiness> businessTypes) {
        this.businessTypes = businessTypes;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public void setSurveyorName(String surveyorName) {
        this.surveyorName = surveyorName;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
    
    
    
    
    
    public void initiateAll(){
        utilityNumber = null;
        List<Estimation> list = new ArrayList<>();
        Estimation estElect = new Estimation(1,"0","Ectric","",0,0,"");
        Estimation estWater = new Estimation(2,"0","Water","",0,0,"");
        
        list.add(estElect);
        list.add(estWater);
        estimation = list;
    }
   
    

    
    public void btnInitiateOnClick(){
        FacesMessage msg = null;
        if(utilityNumber != null)
        {
            surveyorSignature = null;
            surveyByUtilityNumber = surveyBus.findAllByUN(utilityNumber);
            if(surveyByUtilityNumber != null)
            {
                 emirateName = surveyByUtilityNumber.getEmirateName();
                 emirateRegion = surveyByUtilityNumber.getEmirateRegione();
                 substationNumber = surveyByUtilityNumber.getSubstationNumber();
                 premiseBuildingName = surveyByUtilityNumber.getPremiseBuildingName();
                 premiseId = surveyByUtilityNumber.getPremiseId();
                 premiseType = surveyByUtilityNumber.getPremiseType();
                 plotNumber = surveyByUtilityNumber.getPremiseType();
                 longitude = surveyByUtilityNumber.getLongitude().toString();
                 latitude = surveyByUtilityNumber.getLatitude().toString();
                 address = surveyByUtilityNumber.getAddress();
                
                 crEMRequirement_01 = surveyByUtilityNumber.getCrEMRequirement_01();
                 crEMRequirement_02 = surveyByUtilityNumber.getCrEMRequirement_02();
                 crEMRequirement_03 = surveyByUtilityNumber.getCrEMRequirement_03();
                 crEMRequirement_04 = surveyByUtilityNumber.getCrEMRequirement_04();
                 crEMRequirement_05 = surveyByUtilityNumber.getCrEMRequirement_05();
                 crEMRequirement_06 = surveyByUtilityNumber.getCrEMRequirement_06();
                 
                 crWMRequirement_01 = surveyByUtilityNumber.getCrWMRequirement_01();
                 crWMRequirement_02 = surveyByUtilityNumber.getCrWMRequirement_02();
                 crWMRequirement_03 = surveyByUtilityNumber.getCrWMRequirement_03();
                 crWMRequirement_04 = surveyByUtilityNumber.getCrWMRequirement_04();
                 crWMRequirement_05 = surveyByUtilityNumber.getCrWMRequirement_05();
                 crWMRequirement_06 = surveyByUtilityNumber.getCrWMRequirement_06();
                 
                 psEMRequirement_01 = surveyByUtilityNumber.getPsEMRequirement_01();
                 psEMRequirement_02 = surveyByUtilityNumber.getPsEMRequirement_02();
                 psEMRequirement_03 = surveyByUtilityNumber.getPsEMRequirement_03();
                 psEMRequirement_04 = surveyByUtilityNumber.getPsEMRequirement_04();
                 psEMRequirement_05 = surveyByUtilityNumber.getPsEMRequirement_05();
                 
                 psWMRequirement_01 = surveyByUtilityNumber.getPsWMRequirement_01();
                 psWMRequirement_02 = surveyByUtilityNumber.getPsWMRequirement_02();
                 psWMRequirement_03 = surveyByUtilityNumber.getPsWMRequirement_03();
                 psWMRequirement_04 = surveyByUtilityNumber.getPsWMRequirement_04();
                 psWMRequirement_05 = surveyByUtilityNumber.getPsWMRequirement_05();
                 
                 metersEM_AMI = surveyByUtilityNumber.getMetersEM_AMI();
                 metersEM_NAMI = surveyByUtilityNumber.getMetersEM_NAMI();
                 
                 metersWM_AMI = surveyByUtilityNumber.getMetersWM_AMI();
                 metersWM_NAMI = surveyByUtilityNumber.getMetersWM_NAMI();
                 
                 metersEM_Others = surveyByUtilityNumber.getMetersEM_Others();
                 metersWM_Others = surveyByUtilityNumber.getMetersWM_Others();
                 
                 metersWM_MBUS = surveyByUtilityNumber.getMetersWM_MBUS();
                 metersWM_WMBUS = surveyByUtilityNumber.getMetersWM_WMBUS();
                 
                 metersEM_DLMS = surveyByUtilityNumber.getMetersEM_DLMS();
                 metersEM_Euridis = surveyByUtilityNumber.getMetersEM_Euridis();
                 
                 customerCode = surveyByUtilityNumber.getCustomerCode();
                 contactPrimary = surveyByUtilityNumber.getContactPrimary();
                 contactAdditional = surveyByUtilityNumber.getContactAdditional();
                 vendorName = surveyByUtilityNumber.getVendorName();
                 
                 surveyorName = surveyByUtilityNumber.getSurveySurveyor();
                 additionalNotes = surveyByUtilityNumber.getAdditionalNotes();
                 
            }
        }else if(utilityNumber.isEmpty()){
                errorMessage = "Please Enter Utility Number and then press Initiate!";
                msg = new FacesMessage(errorMessage);
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void fetchBomMeters(Long bomID){
       metersElectricity =  bomMetersBus.findAllByBomIdForElectricType(bomID);
       metersWater =  bomMetersBus.findAllByBomIdForWaterType(bomID);
    }
    
    public void saveSurvey(){
        
    }
    
    public void submitSurvey(){
        
    }
    
    public void eraseSignature(){
        
    }
    
    public void startover(){
        utilityNumber = null;
        surveyorSignature = null;
        surveyByUtilityNumber = null;
    }
    
    public void refresh(){
        surveyByUtilityNumber = null;
    }

    public List<MSTEmirates> getEmirates() {
        return emirates;
    }

    public List<MSTEmirateRegions> getEmirateRegions() {
        return emirateRegions;
    }

    public List<MSTPremiseTypes> getPremiseTypes() {
        return premiseTypes;
    }

    public void setEmirates(List<MSTEmirates> emirates) {
        this.emirates = emirates;
    }

    public void setEmirateRegions(List<MSTEmirateRegions> emirateRegions) {
        this.emirateRegions = emirateRegions;
    }

    public void setPremiseTypes(List<MSTPremiseTypes> premiseTypes) {
        this.premiseTypes = premiseTypes;
    }
    public InstallationSemanticView getInstallationSemanticView() {
        return installationSemanticView;
    }

    public void setInstallationSemanticView(InstallationSemanticView installationSemanticView) {
        this.installationSemanticView = installationSemanticView;
    }

    public void findAllCustomers(){
       businessTypes =  businessBus.findAll();
    }
    
    public void findAllVendors(){
       vendors =  vendorBus.findAll();
    }
    
    public void findAllEmirates(){
       emirates =  emirateBus.findAll();
    }
    
    public void findAllEmirateRegions(){
       emirateRegions =  emirateRegionBus.findAll();
    }
    
    public void findAllPremiseTypes(){
       premiseTypes =  premiseTypeBus.findAll();
    }
    public void fetchGatewayMetersSemantics(Long bomId) {
        UtilityGatewayMeterSemantics gmSemantics = bomMetersBus.findGatewayAndMeterSemanticsByBomId(bomId);
        if (gmSemantics != null) {
            setUtilityNumber(gmSemantics.getUtilityNumber());
            installationSemanticView = new InstallationSemanticView(this.utilityNumber,this.selectedRequest.getPremiseType());
            installationSemanticView.setGtwMtrList(gmSemantics);
            installationSemanticView.init();
        }
        else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Internal Server Error<br/> Please Try Again");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
