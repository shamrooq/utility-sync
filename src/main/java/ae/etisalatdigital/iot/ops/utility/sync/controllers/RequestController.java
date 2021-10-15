/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.controllers;

import ae.etisalatdigital.commonUtils.exception.BusinessException;
import ae.etisalatdigital.iot.ops.utility.sync.buses.RequestBus;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.RequestDTO;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author appadmin
 */

@Named(value = "usrequestController")
@SessionScoped
public class RequestController implements Serializable  {
    
    private static final Logger LOGGER = Logger.getLogger(RequestController.class);
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    private RequestDTO selectedRequest;
    private String utilityNumber;
    
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
    
    private String customerCode;
    private String contactPrimary;
    private String contactAdditional;
    
    @Inject
    private RequestBus requestBus;
    
    public static Logger getLOGGER() {
        return LOGGER;
    }

    public RequestDTO getSelectedRequest() {
        return selectedRequest;
    }

    public String getUtilityNumber() {
        return utilityNumber;
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
    
    public String getVendorName() {
        return vendorName;
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
    
    /**
     * 
     * @param selectedRequest 
     */

    public void setSelectedRequest(RequestDTO selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    public void setUtilityNumber(String utilityNumber) {
        this.utilityNumber = utilityNumber;
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
    
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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
    
    /**
     * 
     * @throws BusinessException 
     */
    
    public void updateSelected()  throws BusinessException {
        selectedRequest = requestBus.findAllByUN(utilityNumber);
        
        emirateName = selectedRequest.getEmirateName();
        emirateRegion = selectedRequest.getEmirateRegionName();
        substationNumber = selectedRequest.getPremiseSubStationNumber();
        premiseBuildingName = selectedRequest.getPremiseBuildingName();
        premiseId = selectedRequest.getPremiseId();
        premiseType = selectedRequest.getPremiseType();
        
        plotNumber = selectedRequest.getPremisePlotNumber();
        plotNumber = plotNumber == null ? "" : plotNumber;
        
        latitude = selectedRequest.getPremiseLatitude().toString();
        longitude = selectedRequest.getPremiseLongitude().toString();
        address = selectedRequest.getPremiseAddress();
        vendorName = "IN-HOUSE";
        
        customerCode = selectedRequest.getCustomerCompany();
        contactPrimary = selectedRequest.getContactNumberPrimary();
        contactAdditional = selectedRequest.getContactNumberSecondary();
    }
    
    public void saveUpdateDetails() throws BusinessException{
        if(selectedRequest == null){
            return;
        }
        selectedRequest.setPremiseBuildingName(premiseBuildingName);
        selectedRequest.setPremiseId(premiseId);
        selectedRequest.setPremiseType(premiseType);
        selectedRequest.setPremisePlotNumber(plotNumber == null ? "" : plotNumber);
        selectedRequest.setPremiseAddress(address);
        selectedRequest.setPremiseLatitude(new BigDecimal(latitude));
        selectedRequest.setPremiseLongitude(new BigDecimal(longitude));
        selectedRequest.setContactNumberPrimary(contactPrimary);
        selectedRequest.setContactNumberSecondary(contactAdditional);
        requestBus.updateSRDetails(selectedRequest);
    }
}
