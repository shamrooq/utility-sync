/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.controllers;

import ae.etisalatdigital.iot.ops.utility.sync.buses.BOMMeterBus;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import static ae.etisalatdigital.iot.ops.utility.sync.util.UtilityConstants.COMMA_STR;
import ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.HESClient;
import ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models.EquipmentResponseModel;

import org.primefaces.model.DualListModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;

/**
 *
 * @author au_mobility
 */

@Named(value = "bomMetersController")
@SessionScoped
public class BOMMetersController implements Serializable  {

    private static final Logger LOGGER = Logger.getLogger(BOMMetersController.class);

    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private BOMMeterDTO meterRecord;

    private Long bomId;
    private String utilityNumber;
    private String errorMessage;

    private String meterBOMType;
    private String meterSerial;
    private String meterAmi;
    private Long meterManufacturerId;
    private Long meterModelId;
    private Long meterProtocolId;
    private Long meterRoomTypeId;
    private Long meterFloorTypeId;



    private String wmeterManufacturer;
    private String wmeterSerial;
    private String wmeterModel;
    private String wmeterType;
    private String wmeterStatus;


    private String emeterManufacturer;
    private String emeterSerial;
    private String emeterModel;
    private String emeterType;
    private String emeterStatus;

    List<BOMMeterDTO> meters;
    List<BOMMeterDTO> metersElectricity;
    List<BOMMeterDTO> metersWater;
    List<BOMMeterDTO> selectedMeterForDetails;
    BOMMeterDTO selectedMeter;

    private Long wmeterRoomTypeId;
    private Long wmeterFloorTypeId;

    private Long emeterRoomTypeId;
    private Long emeterFloorTypeId;

    private Long wmeterProtocolId;
    private Long emeterProtocolId;

    private Long wmeterModelId;
    private Long emeterModelId;
    private Long wmeterManufacturerId;
    private Long emeterManufacturerId;

    List<BOMMeterDTO> metersSource;
    List<BOMMeterDTO> metersTarget;
    private DualListModel<BOMMeterDTO> bomMeterModel;

    @Inject
    private BOMMeterBus bomMetersBus;

    @Inject
    private HESClient hesClient;



    public BOMMetersController() {
        metersSource = new ArrayList<>();
        metersTarget = new ArrayList<>();
        this.bomMeterModel = new DualListModel<>(metersTarget, metersSource);
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }


    public EntityManager getEm() {
        return em;
    }

    public String getUtilityNumber() {
        return utilityNumber;
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

    public List<BOMMeterDTO> getMeters() {
        return meters;
    }

    public List<BOMMeterDTO> getSelectedMeterForDetails() {
        return selectedMeterForDetails;
    }

    public BOMMeterDTO getSelectedMeter() {
        return selectedMeter;
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

    public void setMetersElectricity(List<BOMMeterDTO> metersElectricity) {
        this.metersElectricity = metersElectricity;
    }

    public void setMetersWater(List<BOMMeterDTO> metersWater) {
        this.metersWater = metersWater;
    }

    public void setMeters(List<BOMMeterDTO> meters) {
        this.meters = meters;
    }

    public void setSelectedMeterForDetails(List<BOMMeterDTO> selectedMeterForDetails) {
        this.selectedMeterForDetails = selectedMeterForDetails;
    }

    public void setSelectedMeter(BOMMeterDTO selectedMeter) {
        this.selectedMeter = selectedMeter;
    }





    public void refresh(){

    }

    public void initiateAll(){
        utilityNumber = null;

    }

    /**
     *
     * @param event
     * @return
     */

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

    public void fetchBomMeters(Long bomId){
        this.bomId = bomId;
        metersElectricity =  bomMetersBus.findAllByBomIdForElectricType(bomId);
        metersWater =  bomMetersBus.findAllByBomIdForWaterType(bomId);
    }
    /**
     * get all meters(electric & water) associated with a BOM id and a utility ref number
     * @param bomId
     */
    public void fetchBomMetersWithMeterModel(Long bomId) {
        this.bomId = bomId;
        metersElectricity = bomMetersBus.findAllByBomIdForElectricType(bomId);
        metersWater = bomMetersBus.findAllByBomIdForWaterType(bomId);
        meters = new ArrayList<>();
        meters.addAll(metersWater);
        meters.addAll(metersElectricity);
    }

    public String getTotalMeters(){
        Integer totalMeters = 0;
        if (metersElectricity != null && metersWater != null) {
            totalMeters = metersElectricity.size() + metersWater.size();
        }
        return totalMeters.toString();
    }

    public void addNewMeter(){

        String errormsg = "New Meter Added Successfully";
        FacesMessage msg = null;

        if(meterBOMType == null || meterBOMType.isEmpty()){
            msg = new FacesMessage("Validation","Please Serlect Meter Medium!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("Failure", msg);
        }else if(meterSerial == null || meterSerial.isEmpty()){
            msg = new FacesMessage("Validation","Please Enter Meter Serial!");
            msg.setSeverity(FacesMessage.SEVERITY_FATAL);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else if(meterAmi == null || meterAmi.isEmpty()){
            msg = new FacesMessage("Validation","Please Select Valid AMI!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else if(meterManufacturerId == null || meterManufacturerId <= 0){
            msg = new FacesMessage("Validation","Please Select Valid Manufacturer!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else if(meterModelId == null || meterModelId <= 0){
            msg = new FacesMessage("Validation","Please Select Valid Meter Model!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else if(meterProtocolId == null || meterProtocolId <= 0){
            msg = new FacesMessage("Validation","Please Select Valid Meter Protocol!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else if(meterRoomTypeId == null || meterRoomTypeId <= 0){
            msg = new FacesMessage("Validation","Please Select Valid Room!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else if(meterFloorTypeId == null || meterFloorTypeId <= 0){
            msg = new FacesMessage("Validation","Please Select Valid Floor!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else{

            if(meterBOMType.equals("WATER")){
                metersWater = bomMetersBus.addNewMeterByBomId(bomId, meterBOMType, meterSerial, meterAmi, meterManufacturerId, meterModelId, meterProtocolId, meterRoomTypeId, meterFloorTypeId);

            }else{
                metersElectricity = bomMetersBus.addNewMeterByBomId(bomId, meterBOMType, meterSerial, meterAmi, meterManufacturerId, meterModelId, meterProtocolId, meterRoomTypeId, meterFloorTypeId);

            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Success", errormsg);
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }



    }

    public void addNewBOMWM(){
        metersWater = bomMetersBus.addNewMeterByBomId(bomId, "WATER", wmeterManufacturer, wmeterSerial, wmeterModel, wmeterType);

    }

    public void addNewBOMEM(){
        metersElectricity = bomMetersBus.addNewMeterByBomId(bomId, "ELECTRIC", emeterManufacturer, emeterSerial, emeterModel, emeterType);

    }
    public void deleteBOMWM(Long meterID){
        if(meterID != null){
            metersWater = bomMetersBus.deleteMeter(meterID,bomId,"WATER");
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Failure", "New Work Order Initiated WO: "));
        }

    }

    public void deleteBOMEM(Long meterID){
        if(meterID != null){
            metersElectricity = bomMetersBus.deleteMeter(meterID,bomId,"ELECTRIC");
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Failure", "Failed to delete requested meter: "));
        }

    }

    public void saveBOMEM(BOMMeterDTO emtr){
        emtr.setBomMeterType("ELECTRIC");
        bomMetersBus.updateMeterDetails(emtr);

    }

    public void saveBOMWM(BOMMeterDTO wmtr){
        wmtr.setBomMeterType("WATER");
        bomMetersBus.updateMeterDetails(wmtr);
    }

    /**
     *
     * @return
     */
    public BOMMeterDTO getMeterRecord() {
        return meterRecord;
    }

    public String getWmeterManufacturer() {
        return wmeterManufacturer;
    }

    public String getWmeterSerial() {
        return wmeterSerial;
    }

    public String getWmeterModel() {
        return wmeterModel;
    }

    public String getWmeterType() {
        return wmeterType;
    }

    public String getWmeterStatus() {
        return wmeterStatus;
    }

    public String getEmeterManufacturer() {
        return emeterManufacturer;
    }

    public String getEmeterSerial() {
        return emeterSerial;
    }

    public String getEmeterModel() {
        return emeterModel;
    }

    public String getEmeterType() {
        return emeterType;
    }

    public String getEmeterStatus() {
        return emeterStatus;
    }

    public BOMMeterBus getBomMetersBus() {
        return bomMetersBus;
    }

    public Long getBomId() {
        return bomId;
    }

    public Long getWmeterRoomTypeId() {
        return wmeterRoomTypeId;
    }

    public Long getWmeterFloorTypeId() {
        return wmeterFloorTypeId;
    }

    public Long getEmeterRoomTypeId() {
        return emeterRoomTypeId;
    }

    public Long getEmeterFloorTypeId() {
        return emeterFloorTypeId;
    }


    public Long getWmeterProtocolId() {
        return wmeterProtocolId;
    }

    public Long getEmeterProtocolId() {
        return emeterProtocolId;
    }

    public Long getWmeterModelId() {
        return wmeterModelId;
    }

    public Long getEmeterModelId() {
        return emeterModelId;
    }

    public Long getWmeterManufacturerId() {
        return wmeterManufacturerId;
    }

    public Long getEmeterManufacturerId() {
        return emeterManufacturerId;
    }

    public String getMeterBOMType() {
        return meterBOMType;
    }

    public String getMeterSerial() {
        return meterSerial;
    }

    public String getMeterAmi() {
        return meterAmi;
    }

    public Long getMeterManufacturerId() {
        return meterManufacturerId;
    }

    public Long getMeterModelId() {
        return meterModelId;
    }

    public Long getMeterRoomTypeId() {
        return meterRoomTypeId;
    }

    public Long getMeterFloorTypeId() {
        return meterFloorTypeId;
    }

    public Long getMeterProtocolId() {
        return meterProtocolId;
    }

    /**
     *
     * @param meterRecord
     */
    public void setMeterRecord(BOMMeterDTO meterRecord) {
        this.meterRecord = meterRecord;
    }

    public void setWmeterManufacturer(String wmeterManufacturer) {
        this.wmeterManufacturer = wmeterManufacturer;
    }

    public void setWmeterSerial(String wmeterSerial) {
        this.wmeterSerial = wmeterSerial;
    }

    public void setWmeterModel(String wmeterModel) {
        this.wmeterModel = wmeterModel;
    }

    public void setWmeterType(String wmeterType) {
        this.wmeterType = wmeterType;
    }

    public void setWmeterStatus(String wmeterStatus) {
        this.wmeterStatus = wmeterStatus;
    }

    public void setEmeterManufacturer(String emeterManufacturer) {
        this.emeterManufacturer = emeterManufacturer;
    }

    public void setEmeterSerial(String emeterSerial) {
        this.emeterSerial = emeterSerial;
    }

    public void setEmeterModel(String emeterModel) {
        this.emeterModel = emeterModel;
    }

    public void setEmeterType(String emeterType) {
        this.emeterType = emeterType;
    }

    public void setEmeterStatus(String emeterStatus) {
        this.emeterStatus = emeterStatus;
    }

    public void setBomMetersBus(BOMMeterBus bomMetersBus) {
        this.bomMetersBus = bomMetersBus;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public void setWmeterRoomTypeId(Long wmeterRoomTypeId) {
        this.wmeterRoomTypeId = wmeterRoomTypeId;
    }

    public void setWmeterFloorTypeId(Long wmeterFloorTypeId) {
        this.wmeterFloorTypeId = wmeterFloorTypeId;
    }

    public void setEmeterRoomTypeId(Long emeterRoomTypeId) {
        this.emeterRoomTypeId = emeterRoomTypeId;
    }

    public void setEmeterFloorTypeId(Long emeterFloorTypeId) {
        this.emeterFloorTypeId = emeterFloorTypeId;
    }

    public void setWmeterProtocolId(Long wmeterProtocolId) {
        this.wmeterProtocolId = wmeterProtocolId;
    }

    public void setEmeterProtocolId(Long emeterProtocolId) {
        this.emeterProtocolId = emeterProtocolId;
    }

    public void setWmeterModelId(Long wmeterModelId) {
        this.wmeterModelId = wmeterModelId;
    }

    public void setEmeterModelId(Long emeterModelId) {
        this.emeterModelId = emeterModelId;
    }

    public void setWmeterManufacturerId(Long wmeterManufacturerId) {
        this.wmeterManufacturerId = wmeterManufacturerId;
    }

    public void setEmeterManufacturerId(Long emeterManufacturerId) {
        this.emeterManufacturerId = emeterManufacturerId;
    }

    public void setMeterBOMType(String meterBOMType) {
        this.meterBOMType = meterBOMType;
    }

    public void setMeterSerial(String meterSerial) {
        this.meterSerial = meterSerial;
    }

    public void setMeterAmi(String meterAmi) {
        this.meterAmi = meterAmi;
    }

    public void setMeterManufacturerId(Long meterManufacturerId) {
        this.meterManufacturerId = meterManufacturerId;
    }

    public void setMeterModelId(Long meterModelId) {
        this.meterModelId = meterModelId;
    }

    public void setMeterRoomTypeId(Long meterRoomTypeId) {
        this.meterRoomTypeId = meterRoomTypeId;
    }

    public void setMeterFloorTypeId(Long meterFloorTypeId) {
        this.meterFloorTypeId = meterFloorTypeId;
    }

    public void setMeterProtocolId(Long meterProtocolId) {
        this.meterProtocolId = meterProtocolId;
    }



    public void onUtilityTypeChange(){



        //FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "CHANGE","Selected: "+meterBOMType);
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void meterConfigurations(String medium,BOMMeterDTO selectedMeter){
        //String test = "Test";
        //hesClient.GetAllDevices();

        selectedMeterForDetails =  new ArrayList<>();
        selectedMeterForDetails.add(selectedMeter);
        this.selectedMeter = selectedMeter;
        selectedMeter.setBomMeterType(medium);

        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 800);
        options.put("height", 500);
        options.put("resizable", true);
        options.put("draggable", false);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        PrimeFaces.current().dialog().openDynamic("viewMeterDetails", options, null);
    }

    public void addNewMeterOnHES(BOMMeterDTO selectedMeter){
        //if(){
        //    selectedMeter.setMeterStatus("ONHES");
        //}
        hesClient.addNewMeterOnHES(selectedMeter);
    }

    public void onHESConfirmation(SelectEvent event) {
        //Product product = (Product) event.getObject();
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Product Selected", "Name:" + product.getName());

        //FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onHESCancellation(SelectEvent event) {

    }
    public void onRowEdit(RowEditEvent<BOMMeterDTO> event) {
        FacesMessage msg = new FacesMessage("Product Edited", ((BOMMeterDTO)event.getObject()).getMeterSerial());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<BOMMeterDTO> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((BOMMeterDTO)event.getObject()).getMeterSerial());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public DualListModel<BOMMeterDTO> getBomMeterModel() {
        if (this.bomMeterModel == null) {
            bomMeterModel = new DualListModel<>(this.metersTarget, this.metersSource);
        }
        bomMeterModel.setSource(this.metersSource);
        bomMeterModel.setTarget(this.metersTarget);
        return bomMeterModel;
    }

    public void setBomMeterModel(DualListModel<BOMMeterDTO> bomMeterModel) {
        this.bomMeterModel = bomMeterModel;
    }

    public void fetchGatewayBomMeterModel(BigInteger gatewayId) {
        //re-initialise the source and target
        if (null != this.meters) {
            //source should be all meters with no association with any gateway
            this.metersSource = this.meters.stream().filter(meter -> (null == meter.getMeterGateway().getId()))
                    .collect(Collectors.toList());
            //target should be all meters with an association with the given gateway
            this.metersTarget = this.meters.stream().filter(meter -> (gatewayId.equals(meter.getMeterGateway().getId())))
                    .collect(Collectors.toList());
        }
        this.getBomMeterModel();
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        List<BOMMeterDTO> metersToTarget, metersToSource = null;
        DualListModel model = getBomMeterModel();
        if (event.isAdd()) {
            metersToTarget = (List<BOMMeterDTO>) event.getItems();
            for (BOMMeterDTO item : metersToTarget) {
                builder.append(item.getId()).append(COMMA_STR);
            }
            this.metersSource.removeAll(metersToTarget);
            this.metersTarget.addAll(metersToTarget);
        } else {
            metersToSource = (List<BOMMeterDTO>) event.getItems();
            for (BOMMeterDTO item : metersToSource) {
                builder.append(item.getId()).append(COMMA_STR);
            }
            this.metersSource.addAll(metersToSource);
            this.metersTarget.removeAll(metersToSource);
        }
        model.setSource(this.metersTarget);
        model.setTarget(this.metersSource);
        LOGGER.debug("Items transferred " + builder.deleteCharAt(builder.lastIndexOf(COMMA_STR)).toString());
        /*FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
    }

    /**
     * method to map meter with a parent gateway
     */
    private int updateMeterMappingWithHES(BOMGatewayEstDTO gateway) {
        List<EquipmentResponseModel> equipmentResponseModelList=null;
        try{
            equipmentResponseModelList = hesClient.updateGtwToMeterMappingOnHES(gateway,this.metersTarget);
            equipmentResponseModelList.forEach(eq->{
                addMessage(null, eq, null);
            });

        } catch (Exception e) {
            addMessage(null, null, e.getMessage());
            return HttpStatus.SC_INTERNAL_SERVER_ERROR;
        }
        for(EquipmentResponseModel eq : equipmentResponseModelList){
            if(null!=eq.getErrorNumber()){
                return HttpStatus.SC_INTERNAL_SERVER_ERROR;
            }
        }
        return HttpStatus.SC_OK;
    }

    public void updateGtwBomMeterMapping(BOMGatewayEstDTO gateway) {
        if (this.metersTarget == null && this.metersTarget == null) {
            return;
        }
        int response = updateMeterMappingWithHES(gateway);
        if(response==HttpStatus.SC_OK){
            saveMeterMapping(gateway);
        }
    }

    private void saveMeterMapping(BOMGatewayEstDTO gateway){
        int i;
        for (BOMMeterDTO meterDTO : this.metersTarget) {
            i = meters.indexOf(meterDTO);
            if (i >= 0) {
                //map the parent gateway to the meter
                meters.get(i).setMeterGateway(
                        new BOMGatewayEstDTO.Builder(gateway.getId(),gateway.getBomId()).build());
            }
        }
        for (BOMMeterDTO meterDTO : this.metersSource) {
            i = meters.indexOf(meterDTO);
            if (i >= 0) {
                //remove the parent gateway mapping for the meter
                meters.get(i).setMeterGateway(null);
            }
        }
        FacesMessage msg;
        try {
            bomMetersBus.updateMeterAll(gateway, this.metersSource, this.metersTarget);
            for (BOMMeterDTO meterDTO : this.metersTarget) {
                i = meters.indexOf(meterDTO);
                if (i >= 0) {
                    //map the parent gateway to the meter
                    meters.get(i).setMeterGateway(
                            new BOMGatewayEstDTO.Builder(gateway.getId(),gateway.getBomId()).build());
                }
            }
            for (BOMMeterDTO meterDTO : this.metersSource) {
                i = meters.indexOf(meterDTO);
                if (i >= 0) {
                    //remove the parent gateway mapping for the meter
                    meters.get(i).setMeterGateway(null);
                }
            }
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Gateway - Meter mapping completed for gateway with id -> " + gateway.getId());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error during Gateway - Meter mapping for gateway with id " + gateway.getId());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            LOGGER.error("Exception during sql update", e);
        }
    }
    /**
     * add message on JSF view
     *
     * @param clientId
     * @param equipmentResponseModel
     * @param message
     * @return status code
     */
    private int addMessage(String clientId,EquipmentResponseModel equipmentResponseModel,String message) {
        FacesMessage facesMessage;
        if (null != equipmentResponseModel) {
            if (Long.valueOf(200).equals(equipmentResponseModel.getCode())) {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "", message==null?
                        equipmentResponseModel.getDescription():message);
                FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
            } else if (null != equipmentResponseModel.getErrorNumber()) {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(equipmentResponseModel.getErrorNumber()),
                        Optional.ofNullable(equipmentResponseModel.getErrorCode()).orElse("").concat(
                                equipmentResponseModel.getStackTrace()==null?"":equipmentResponseModel.getStackTrace()));
                FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
                return HttpStatus.SC_INTERNAL_SERVER_ERROR;
            }
        } else {
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ""+HttpStatus.SC_INTERNAL_SERVER_ERROR,
                    message==null?"Internal Server Error":message);
            FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
            return HttpStatus.SC_INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.SC_OK;
    }
}
