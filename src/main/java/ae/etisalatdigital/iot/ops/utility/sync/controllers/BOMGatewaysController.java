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
import ae.etisalatdigital.iot.ops.utility.sync.entities.BOMGatewaysEst;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Requests;
import ae.etisalatdigital.iot.ops.utility.sync.util.MethodUtils;
import ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.HESClient;
import ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models.EquipmentResponseModel;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.CloseEvent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

        String detailMsg = "Gateway Added Successfully";
        FacesMessage msg;
        gatewaysRequired = 1;

        if(gatewaysType.isEmpty()){
            MethodUtils.addMessage(FacesMessage.SEVERITY_ERROR,"Failure","Validation","Please Select Gateway");
        }else if(gatewaysTypeProposed.isEmpty()){
            MethodUtils.addMessage(FacesMessage.SEVERITY_ERROR,null,"Validation","Please Select Gateway Type");
        }else if(cableLength.equals(0)){
            MethodUtils.addMessage(FacesMessage.SEVERITY_ERROR,null,"Validation","Please Enter Cable Length");
        }else if(signalStrength == 0){
            MethodUtils.addMessage(FacesMessage.SEVERITY_ERROR,null,"Validation","Please Enter Signal Strength");
        }
        else{
            metersPerGateway = 0;
            estimation = gatewayEstBus.addNewGatewayEstByBomId(bomId, gatewaysType, gatewaysTypeProposed, gatewaysRequired, metersPerGateway,cableLength,gatewayRoomId,gatewayFloorId,powerIntruption,signalStrength,antenaRequired);
            MethodUtils.addMessage(FacesMessage.SEVERITY_ERROR,null,"Success",detailMsg);
        }
    }

    public void deleteGatewayEst(Long gatewayID) {
        if (gatewayID != null) {
            estimation = gatewayEstBus.deleteGateway(gatewayID, bomId);
        } else {
            MethodUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                    null, "Failure", "Failed to delete requested meter: ");
        }
    }

    public void saveGatewayEst(BOMGatewayEstDTO gateway) {
        gatewayEstBus.updateGatewayDetails(gateway);
    }

    public int updateGatewayEstWithHES(Requests utilityReq, BOMGatewayEstDTO gatewayItem) {
        LOGGER.info("updateGatewayEstWithHES called");
        EquipmentResponseModel equipmentResponseModel=null;
        try{
            equipmentResponseModel = hesClient.updateGatewayOnHES(utilityReq,gatewayItem);
        }
        catch (Exception e) {
            MethodUtils.addMessage(FacesMessage.SEVERITY_ERROR,null, null, e.getMessage());
        }
        return MethodUtils.addMessage(null,equipmentResponseModel, "Gateway defined with HES");
    }

    /**
     * add gateway with HES and if successful store the definition in our local database.
     * @param utilityReq
     * @param gatewayItem
     *
     */
    public void saveGatewayEstimation(Requests utilityReq, BOMGatewayEstDTO gatewayItem) {
        int httpStatusCode = updateGatewayEstWithHES(utilityReq,gatewayItem);
        if (httpStatusCode == HttpStatus.SC_OK) {
            LOGGER.info("BOMGatewayEstDTO.saveGatewayEstimation called");
            gatewayEstBus.updateGatewayDetails(gatewayItem);
            MethodUtils.addMessage(FacesMessage.SEVERITY_INFO,null,"",
                    "Gateway with serial number -> "+gatewayItem.getSerialNumber() +
                            " successfully defined with HES");
        }
    }

    public void handleSimDialogClose(CloseEvent event) {
        UIComponent dialog = (org.primefaces.component.dialog.Dialog)event.getSource();
        if(null!=dialog.getParent().getChildren().get(0))
        {
            try{
                HtmlInputText text = (HtmlInputText)dialog.getParent().getChildren().get(0);
                InputText simIccidTextField=((InputText)dialog.getChildren().get(1).getChildren().get(0));
                text.setValue(simIccidTextField.getValue());
            }
            catch(Exception e){
                LOGGER.error("Exception ",e);
            }
        }
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Adding SIM completed.");
        //FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addSIMWithHES(BOMGatewayEstDTO gateway) {
        //BigInteger simIccid = gateway.getSimICCID();
        this.simDetailsDTO = gateway.getSimDetailsDTO();
        simDetailsBus.addNewSimDetails(simDetailsDTO);
        BOMGatewaysEst gatewayEntity = gatewayEstBus.updateGatewayDetails(gateway);
        EquipmentResponseModel equipmentResponseModel;
        try{
            if(null!=gatewayEntity){
                gateway.getSimDetailsDTO().setId(gatewayEntity.getSimDetails().getId());
            }
            equipmentResponseModel = hesClient.addNewSimOnHES(gateway);
            if (equipmentResponseModel != null) {
                if ( (null != equipmentResponseModel.getCode() && Long.valueOf(200).equals(equipmentResponseModel.getCode()))
                        || (null!=equipmentResponseModel.getStackTrace() && equipmentResponseModel.getStackTrace().contains("already exists"))) {
                    if (null != gateway.getSimICCID() && !(gateway.getSimICCID().equals(gateway.getSimDetailsDTO().getSimICCID()))) {
                        gateway.setSimICCID(gateway.getSimDetailsDTO().getSimICCID());
                    }
                    MethodUtils.addMessage("createSimHESWdg" + gateway.getId(), equipmentResponseModel, "SIM added with HES");
                } else {
                    MethodUtils.addMessage("createSimHESForm", equipmentResponseModel, null);
                }
            } else {
                MethodUtils.addMessage(null, null, null);
            }
        } catch (Exception e) {
            MethodUtils.addMessage(null, null, e.getMessage());
        }
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

    public SimDetailsDTO getSimDetailsDTO() {
        return simDetailsDTO;
    }

    public void setSimDetailsDTO(SimDetailsDTO simDetailsDTO) {
        this.simDetailsDTO = simDetailsDTO;
    }
}