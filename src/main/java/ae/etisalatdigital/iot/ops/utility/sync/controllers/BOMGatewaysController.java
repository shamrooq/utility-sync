/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.controllers;

import ae.etisalatdigital.iot.ops.utility.sync.buses.BOMGatewayEstBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.SimDetailsBus;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.SimDetailsDTO;
import ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.HESClient;
import ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models.EquipmentResponseModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.primefaces.event.CloseEvent;

/**
 *
 * @author au_mobility
 */
@Named(value = "bomGatewaysController")
@SessionScoped
public class BOMGatewaysController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(BOMGatewaysController.class);

    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    private BOMGatewayEstDTO gatewayEstRecord;
    private SimDetailsDTO simDetailsDTO;
    private String utilityNumber;
    private Long bomId;
    private String errorMessage;
    private String gatewaysType;
    private String gatewaysTypeProposed;
    private int gatewaysRequired;
    private int metersPerGateway;

    private Double cableLength;

    private String gatewaysVendor;
    private String gatewaysLocation;
    private String gatewaysDaisychain;
    private String gatewaysChainLabel;

    private Boolean powerIntruption;
    private Long signalStrength;
    private String signalStrengthIndicator;
    private Boolean antenaRequired;

    List<BOMGatewayEstDTO> estimation;
    
    List<SimDetailsDTO> simDetailList;

    private Long gatewayRoomId;
    private Long gatewayFloorId;
    private int rowsPerPage = 10;
    String rowsPerPageTemplate="10";

    @Inject
    private BOMGatewayEstBus gatewayEstBus;
    @Inject
    private HESClient hesClient;
    @Inject
    private SimDetailsBus simDetailsBus;
    
    public static Logger getLOGGER() {
        return LOGGER;
    }

    public BOMGatewaysController() {
    }

    public void initiateAll() {
        List<BOMGatewayEstDTO> list = new ArrayList<>();
        estimation = list;
    }

    public void refresh() {
        estimation = null;

        List<BOMGatewayEstDTO> list = new ArrayList<>();
        estimation = list;
    }

    public void findAllEstimations() {
        List<BOMGatewayEstDTO> list = new ArrayList<>();
        estimation = list;
    }

    public void updateEstimation(Long bomId){
            this.bomId = bomId;
            estimation = gatewayEstBus.findAllByBomId(bomId);
            if(estimation == null){
                List<BOMGatewayEstDTO> list = new ArrayList<>();
                estimation = list;
            }
    }

    public void updateGtwEstimation(Long bomId){
        this.bomId = bomId;
        estimation = gatewayEstBus.findSomeByBomId(bomId);
        if (estimation == null) {
            List<BOMGatewayEstDTO> list = new ArrayList<>();
            estimation = list;
            
        }
        if (estimation.size() >= 20) {
            rowsPerPageTemplate = "10,20," + estimation.size();
        } else if (estimation.size() >= 10) {
            rowsPerPageTemplate = "10," + estimation.size();
        } else {
            rowsPerPageTemplate = ""+estimation.size();
        }
        setRowsPerPageTemplate(rowsPerPageTemplate);
    }
    public String getUtilityNumber() {
        return utilityNumber;
    }

    public Long getBomId() {
        return bomId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public BOMGatewayEstDTO getGatewayEstRecord() {
        return gatewayEstRecord;
    }

    public List<BOMGatewayEstDTO> getEstimation() {
        return estimation;
    }

    public void setUtilityNumber(String utilityNumber) {
        this.utilityNumber = utilityNumber;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setGatewayEstRecord(BOMGatewayEstDTO gatewayEstRecord) {
        this.gatewayEstRecord = gatewayEstRecord;
    }

    public void setEstimation(List<BOMGatewayEstDTO> estimation) {
        this.estimation = estimation;
    }


     public void addNewGatewayEst(){

         String errormsg = "Gateway Added Successfully";
         FacesMessage msg;
         gatewaysRequired = 1;

         if(gatewaysType.isEmpty()){
                msg = new FacesMessage("Validation","Please Serlect Gateway");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage("Failure", msg);
         }else if(gatewaysTypeProposed.isEmpty()){
                msg = new FacesMessage("Validation","Please Serlect Gateway Type");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
         }else if(cableLength.equals(0)){
             msg = new FacesMessage("Validation","Please Eter Cable Length");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
         }else if(signalStrength == 0){
             msg = new FacesMessage("Validation","Please Eter Signal strength");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
         }
         else{
              metersPerGateway = 0;
              estimation = gatewayEstBus.addNewGatewayEstByBomId(bomId, gatewaysType, gatewaysTypeProposed, gatewaysRequired, metersPerGateway,cableLength,gatewayRoomId,gatewayFloorId,powerIntruption,signalStrength,antenaRequired);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success",errormsg));
         }
    }

    public void deleteGatewayEst(Long gatewayID) {
        if (gatewayID != null) {
            estimation = gatewayEstBus.deleteGateway(gatewayID, bomId);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Failure", "Failed to delete requested meter: "));
        }
    }

    public void saveGatewayEst(BOMGatewayEstDTO gateway) {
        gatewayEstBus.updateGatewayDetails(gateway);
    }

    public void addGatewayEstWithHES(BOMGatewayEstDTO gatewayItem) {
        LOGGER.info("BOMGatewayEstDTO.addGatewayEstWithHES called");
        EquipmentResponseModel equipmentResponseModel = hesClient.addNewGatewayOnHES(gatewayItem);
        addMessage(equipmentResponseModel, "Gateway defined with HES");
    }
    
    public BOMGatewayEstDTO saveGatewayEstimation(BOMGatewayEstDTO gatewayItem) {
        LOGGER.info("BOMGatewayEstDTO.saveGatewayEstimation called");
        gatewayEstBus.updateGatewayDetails(gatewayItem);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Details successfully updated for gateway with id -> "+
                gatewayItem.getId()));
        return gatewayItem;
    }
    
    public void handleSimDialogClose(CloseEvent event) {
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Adding SIM completed.");
        //FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void addSIMWithHES(BOMGatewayEstDTO gateway){
        //BigInteger simIccid = gateway.getSimICCID();
        EquipmentResponseModel equipmentResponseModel = hesClient.addNewSimOnHES(gateway);
        if(equipmentResponseModel!=null && Long.valueOf(200).equals(equipmentResponseModel.getCode())){
            simDetailsBus.addNewSimDetails(simDetailsDTO);
            if(null!=gateway.getSimICCID() && !(gateway.getSimICCID().equals(gateway.getSimDetailsDTO().getSimICCID()))){
                gateway.setSimICCID(gateway.getSimDetailsDTO().getSimICCID());
            }
        }
        addMessage(equipmentResponseModel, "SIM added with HES");
        
    }    
    /**
     *
     * @return string gatewaysType
     */
    public String getGatewaysType() {
        return gatewaysType;
    }

    public String getGatewaysTypeProposed() {
        return gatewaysTypeProposed;
    }

    public int getGatewaysRequired() {
        return gatewaysRequired;
    }

    public int getMetersPerGateway() {
        return metersPerGateway;
    }

    public Double getCableLength() {
        return cableLength;
    }



    public String getGatewaysVendor() {
        return gatewaysVendor;
    }

    public String getGatewaysLocation() {
        return gatewaysLocation;
    }

    public String getGatewaysDaisychain() {
        return gatewaysDaisychain;
    }

    public String getGatewaysChainLabel() {
        return gatewaysChainLabel;
    }

    public Boolean getPowerIntruption() {
        return powerIntruption;
    }

    public Long getSignalStrength() {
        return signalStrength;
    }

    public String getSignalStrengthIndicator() {
        return signalStrengthIndicator;
    }

    public Boolean getAntenaRequired() {
        return antenaRequired;
    }

    public Long getGatewayRoomId() {
        return gatewayRoomId;
    }

    public Long getGatewayFloorId() {
        return gatewayFloorId;
    }

    public void setGatewaysType(String gatewaysType) {
        this.gatewaysType = gatewaysType;
    }

    public void setGatewaysTypeProposed(String gatewaysTypeProposed) {
        this.gatewaysTypeProposed = gatewaysTypeProposed;
    }

    public void setGatewaysRequired(int gatewaysRequired) {
        this.gatewaysRequired = gatewaysRequired;
    }

    public void setMetersPerGateway(int metersPerGateway) {
        this.metersPerGateway = metersPerGateway;
    }

    public void setCableLength(Double cableLength) {
        this.cableLength = cableLength;
    }

    public void setGatewaysVendor(String gatewaysVendor) {
        this.gatewaysVendor = gatewaysVendor;
    }

    public void setGatewaysLocation(String gatewaysLocation) {
        this.gatewaysLocation = gatewaysLocation;
    }

    public void setGatewaysDaisychain(String gatewaysDaisychain) {
        this.gatewaysDaisychain = gatewaysDaisychain;
    }

    public void setGatewaysChainLabel(String gatewaysChainLabel) {
        this.gatewaysChainLabel = gatewaysChainLabel;
    }

    public void setPowerIntruption(Boolean powerIntruption) {
        this.powerIntruption = powerIntruption;
    }

    public void setSignalStrength(Long signalStrength) {
        this.signalStrength = signalStrength;
    }

    public void setSignalStrengthIndicator(String signalStrengthIndicator) {
        this.signalStrengthIndicator = signalStrengthIndicator;
    }

    public void setAntenaRequired(Boolean antenaRequired) {
        this.antenaRequired = antenaRequired;
    }

    public void setGatewayRoomId(Long gatewayRoomId) {
        this.gatewayRoomId = gatewayRoomId;
    }

    public void setGatewayFloorId(Long gatewayFloorId) {
        this.gatewayFloorId = gatewayFloorId;
    }
    public int getRowsPerPage() {
        return rowsPerPage;
    }
    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public String getRowsPerPageTemplate() {
        return rowsPerPageTemplate;
    }

    public void setRowsPerPageTemplate(String rowsPerPageTemplate) {
        this.rowsPerPageTemplate = rowsPerPageTemplate;
    }
    private void addMessage(EquipmentResponseModel equipmentResponseModel,String message) {
        if (null != equipmentResponseModel) {
        FacesMessage facesMessage;
        if (equipmentResponseModel.getCode() == 200) {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "", message);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            } else if (null != equipmentResponseModel.getErrorNumber()) {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, equipmentResponseModel.getErrorCode(),
                        equipmentResponseModel.getStackTrace());
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        }
    }

    public SimDetailsDTO getSimDetailsDTO() {
        return simDetailsDTO;
    }

    public void setSimDetailsDTO(SimDetailsDTO simDetailsDTO) {
        this.simDetailsDTO = simDetailsDTO;
    }
}