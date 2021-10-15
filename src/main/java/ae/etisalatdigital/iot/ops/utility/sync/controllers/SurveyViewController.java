/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.controllers;
import ae.etisalatdigital.commonUtils.exception.BusinessException;
import ae.etisalatdigital.iot.ops.utility.sync.beans.OPDevice;
import ae.etisalatdigital.iot.ops.utility.sync.beans.Estimation;
import ae.etisalatdigital.iot.ops.utility.sync.beans.SurveyAction;

import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTBusinessBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTEmirateBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTEmirateRegionBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTGatewayTypeBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTPremiseTypeBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTProtocolBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.MSTVendorBus;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.RequestDTO;


import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTBusiness;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTEmirateRegions;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTEmirates;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTGatewayTypes;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTPremiseTypes;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTProtocol;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTVendors;
import static com.mashape.unirest.http.Unirest.options;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author au_mobility
 */

@Named(value = "surveyViewController")
@SessionScoped
public class SurveyViewController implements Serializable  {
    
    private static final Logger LOGGER = Logger.getLogger(SurveyViewController.class);
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    
   
    private String utilityNumber;
    private String errorMessage;
    private String surveyorSignature;
    
        
    List<MSTVendors> vendors;
    List<MSTEmirates> emirates;
    List<MSTEmirateRegions> emirateRegions;
    List<MSTPremiseTypes> premiseTypes;
    List<MSTBusiness> businessTypes;
    List<MSTProtocol> protocols;
    List<MSTGatewayTypes> gatewayTypes;
    
    
    List<SurveyAction> surveyAction;
    
    
    
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
    private MSTGatewayTypeBus gatewayTypeBus;
    
    @Inject
    private MSTProtocolBus protocolBus;
    
    @Inject
    private SurveyController controllerSurvey;
    
    @Inject
    private RequestController controllerRequest;
    
    @Inject
    private BOMMetersController controllerBomMeters;
    
    @Inject
    private BOMGatewaysController controllerBomGateways;
    
    public static Logger getLOGGER() {
        return LOGGER;
    }

    public SurveyController getControllerSurvey() {
        return controllerSurvey;
    }

    public RequestController getControllerRequest() {
        return controllerRequest;
    }

    public BOMMetersController getControllerBomMeters() {
        return controllerBomMeters;
    }

    public BOMGatewaysController getControllerBomGateways() {
        return controllerBomGateways;
    }
    
    
    
    
    public void setControllerSurvey(SurveyController controllerSurvey) {
        this.controllerSurvey = controllerSurvey;
    }

    public void setControllerRequest(RequestController controllerRequest) {
        this.controllerRequest = controllerRequest;
    }

    public void setControllerBomMeters(BOMMetersController controllerBomMeters) {
        this.controllerBomMeters = controllerBomMeters;
    }

    public void setControllerBomGateways(BOMGatewaysController controllerBomGateways) {
        this.controllerBomGateways = controllerBomGateways;
    }
    
    
    
    public EntityManager getEm() {
        return em;
    }

    public String getUtilityNumber() {
        return utilityNumber;
    }

    public List<MSTVendors> getVendors() {
        return vendors;
    }
    

    public String getErrorMessage() {
        return errorMessage;
    }

    

    public List<SurveyAction> getSurveyAction() {
        return surveyAction;
    }
    
    

    public List<MSTProtocol> getProtocols() {
        return protocols;
    }
    
    
    

    public String getSurveyorSignature() {
        return surveyorSignature;
    }

    

    public List<MSTBusiness> getBusinessTypes() {
        return businessTypes;
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

    

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    

    public void setSurveyAction(List<SurveyAction> surveyAction) {
        this.surveyAction = surveyAction;
    }
    
    

    public void setProtocols(List<MSTProtocol> protocols) {
        this.protocols = protocols;
    }
    
    

    public void setSurveyorSignature(String surveyorSignature) {
        this.surveyorSignature = surveyorSignature;
    }

    

    public void setBusinessTypes(List<MSTBusiness> businessTypes) {
        this.businessTypes = businessTypes;
    }

    

    
    
    
    
    
    
    public void initiateAll(){
        utilityNumber = null;
        surveyorSignature = "";
        
    }
   
    public String onFlowProcess(FlowEvent event) {
        
        return event.getNewStep();
        
    }

    public void submitSurveyFailed(RequestDTO selectedRequest){
       System.out.println("ae.etisalatdigital.iot.ops.utility.sync.controllers.SurveyViewController.submitSurvey()");
       RequestContext context = RequestContext.getCurrentInstance();
       
       controllerSurvey.saveSurveyDetails(selectedRequest,"Failed");
       context.execute("PF('dlg3').show();");
       
       //PrimeFaces.current().dialog().openDynamic("viewProducts", utilityNumber, null);
    }
    
    public void submitSurvey(RequestDTO selectedRequest){
       System.out.println("ae.etisalatdigital.iot.ops.utility.sync.controllers.SurveyViewController.submitSurvey()");
       RequestContext context = RequestContext.getCurrentInstance();
       
       controllerSurvey.saveSurveyDetails(selectedRequest,"Completed");
       context.execute("PF('dlg3').show();");
       
       //PrimeFaces.current().dialog().openDynamic("viewProducts", utilityNumber, null);
    }
    
    public void captureLocation(){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('gmpDlg').show();");
        
    }
    
    public void eraseSignature(){
        
    }
    
    public void startover(){
        utilityNumber = null;
        surveyorSignature = null;
        
    }
    
    public void refreshEstimation(){
        
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

    
    
    public List<MSTGatewayTypes> getGatewayTypes() {
        return gatewayTypes;
    }

    public void setGatewayTypes(List<MSTGatewayTypes> gatewayTypes) {
        this.gatewayTypes = gatewayTypes;
    }
    
    
    
    public void findAllGatewayTypes(){
       gatewayTypes =  gatewayTypeBus.findAll();
    }
    
    
    public void findAllProtocols(){
        protocols = protocolBus.findAll();
    }
    
}
