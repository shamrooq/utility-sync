/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.controllers;
import ae.etisalatdigital.commonUtils.exception.BusinessException;
import ae.etisalatdigital.iot.ops.utility.sync.beans.OPDevice;
import ae.etisalatdigital.iot.ops.utility.sync.beans.Estimation;
import ae.etisalatdigital.iot.ops.utility.sync.buses.BOMMeterBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.BomBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTBusinessBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTEmirateBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTEmirateRegionBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTMeterTypeBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTPremiseTypeBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTStatusBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTUSBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTUserBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTVendorBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.RequestBus;

import ae.etisalatdigital.iot.ops.utility.sync.dtos.SurveyDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Surveys;
import ae.etisalatdigital.iot.ops.utility.sync.buses.SurveyBus;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.MSTVendorDTO;
        
import ae.etisalatdigital.iot.ops.utility.sync.daos.MSTVendorDAO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.MSTUSDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.RequestDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTBusiness;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTEmirateRegions;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTEmirates;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTMeterTypes;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTPremiseTypes;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTProtocol;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTStatus;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTUsers;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTVendors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.SessionScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Null;
import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author au_mobility
 */

@Named(value = "workorderViewController")
@ViewScoped
public class WorkorderViewController implements Serializable  {
    
    private static final Logger LOGGER = Logger.getLogger(WorkorderViewController.class);
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    
    private String workorderStatus;
    private String utilityNumber;
    private String errorMessage;
    private String surveyorSignature;
    
    private BOMDTO bomDTO;
    private MSTUSDTO baselineDTO;
    private RequestDTO requestDTO;
    private BOMMeterDTO meterRecord;
    
    private String emirateName;
    private String emirateRegion;
    private String substationNumber;
    private String premiseBuildingName;
    private String premiseId;
    private String premiseType;
    private String plotNumber;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String address;
    
    private String vendorName;
    
    
    private String customerCode;
    private String contactPrimary;
    private String contactAdditional;
    
    private String surveyorName;
    private String additionalNotes;
    
    private String bomMeterType;
    private Long bomId;
    
    private Date surveyDateSchedule;
    
    
    List<BOMMeterDTO> meters;
    List<MSTVendors> vendors;
    List<MSTEmirates> emirates;
    List<MSTEmirateRegions> emirateRegions;
    List<MSTPremiseTypes> premiseTypes;
    List<MSTMeterTypes> meterTypes;
    List<MSTBusiness> businessTypes;
    List<MSTProtocol> protocols;
    List<MSTStatus> ststuses;
    List<MSTUsers> staffs;
    
    private String meterBomType;
    private String meterManufacturer;
    private String meterSerial;
    private String meterModel;
    private String meterType;
    
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
    private MSTMeterTypeBus meterTypeBus;
    
    @Inject
    private MSTBusinessBus businessBus;
    
    
    @Inject
    private BomBus bomsBus;
    
    @Inject
    private BOMMeterBus bomMetersBus;
    
    @Inject
    private MSTStatusBus statusBus;
    
    @Inject
    private RequestBus requestBus;
    
    @Inject
    private MSTUSBus baselineBus;
    
    @Inject
    private MSTUserBus staffBus;
    
    
    public static Logger getLOGGER() {
        return LOGGER;
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

    public List<BOMMeterDTO> getMeters() {
        return meters;
    }

    public List<MSTUsers> getStaffs() {
        return staffs;
    }

    

    

    public List<MSTProtocol> getProtocols() {
        return protocols;
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

    public BigDecimal getLongitude() {
        return longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
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

    public List<MSTStatus> getStstuses() {
        return ststuses;
    }

    public String getWorkorderStatus() {
        return workorderStatus;
    }

    public List<MSTMeterTypes> getMeterTypes() {
        return meterTypes;
    }

    public String getBomMeterType() {
        return bomMeterType;
    }

    public Long getBomId() {
        return bomId;
    }

    public BOMDTO getBomDTO() {
        return bomDTO;
    }
    
    
    

    public MSTUSDTO getBaselineDTO() {
        return baselineDTO;
    }

    public RequestDTO getRequestDTO() {
        return requestDTO;
    }
    
    
    
    
    /*
    *
    *
    */
    
    

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

    public void setMeters(List<BOMMeterDTO> meters) {
        this.meters = meters;
    }

    public void setStaffs(List<MSTUsers> staffs) {
        this.staffs = staffs;
    }

    

    
    public void setProtocols(List<MSTProtocol> protocols) {
        this.protocols = protocols;
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

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setStstuses(List<MSTStatus> ststuses) {
        this.ststuses = ststuses;
    }

    public void setWorkorderStatus(String workorderStatus) {
        this.workorderStatus = workorderStatus;
    }

    public void setMeterTypes(List<MSTMeterTypes> meterTypes) {
        this.meterTypes = meterTypes;
    }

    public void setBomMeterType(String bomMeterType) {
        this.bomMeterType = bomMeterType;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public void setBomDTO(BOMDTO bomDTO) {
        this.bomDTO = bomDTO;
    }
    
    public void setRequestDTO(RequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }

    public void setBaselineDTO(MSTUSDTO baselineDTO) {
        this.baselineDTO = baselineDTO;
    }
    
    
    
    
    
    
    public void initiateAll(){
        utilityNumber = null;
        
    }
   
    public String onFlowProcess(FlowEvent event) {
        
        return event.getNewStep();
        
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
        
    }
    
    public void refresh(){
        
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
    
    public void findAllMeterTypes(){
       meterTypes =  meterTypeBus.findAll();
    }
    
    public void findAllStatuses(){
        ststuses = statusBus.findAll();
    }
    
    public void findOneBaseline(){
        baselineDTO = baselineBus.findOne();
    }
    
    public void findAllStaff(){
        staffs = staffBus.findAll();
    }
    
    
    
    
    
    public void createWorkOrder(){
        if(emirateName == null || emirateName.isEmpty()){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Failure", "New Work Order Initiated WO: "));
        }else if (emirateRegion == null || emirateRegion.isEmpty()){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Failure", "New Work Order Initiated WO: "));
            
        }else {
            Long usNumber = baselineDTO.getUsNumber() + 1;
            utilityNumber = baselineDTO.getUsCode() + "-" + usNumber ;
            
            requestDTO = requestBus.createNewUSRequest(utilityNumber, 0L, customerCode, emirateName, emirateRegion, substationNumber, vendorName, contactPrimary, substationNumber, latitude, longitude, premiseBuildingName, plotNumber, premiseId, premiseType, premiseId, usNumber, surveyDateSchedule, surveyorSignature, emirateName);
            
            baselineDTO.setUsNumber(usNumber);
        }
    }

    
     
    /**
     * Meter Table
     * @return 
     */
    public String getMeterType() {
        return meterType;
    }
    
    public String getMeterBomType(){
        return meterBomType;
    }

    public String getMeterManufacturer() {
        return meterManufacturer;
    }

    public String getMeterSerial() {
        return meterSerial;
    }

    public String getMeterModel() {
        return meterModel;
    }
    
    public BOMMeterDTO getMeterRecord() {
        return meterRecord;
    }

    public void setMeterBomType(String meterBomType) {
        this.meterBomType = meterBomType;
    }

    public void setMeterManufacturer(String meterManufacturer) {
        this.meterManufacturer = meterManufacturer;
    }

    public void setMeterSerial(String meterSerial) {
        this.meterSerial = meterSerial;
    }

    public void setMeterModel(String meterModel) {
        this.meterModel = meterModel;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public void setMeterRecord(BOMMeterDTO meterRecord) {
        this.meterRecord = meterRecord;
    }
    
    
    
   

    public void initiateBOM() {
        if(requestDTO != null){
            bomDTO = bomsBus.createNewUSBOM(utilityNumber, "BOM Initiated");
        }
    }
    
    public void addNewBOMMeter(){
        meters = bomMetersBus.addNewMeterByBomId(bomDTO.getId(), meterBomType, meterManufacturer, meterSerial, meterModel, meterType);
        
    }
    public void deleteBOMMeter(Long meterID){
        if(meterID != null){
            meters = bomMetersBus.deleteMeter(meterID,bomDTO.getId());
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Failure", "New Work Order Initiated WO: "));
        }
        
    }
    
    public void submitWorkOrder(){
        
    }
    
}
