/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.controllers;

import ae.etisalatdigital.iot.ops.utility.sync.buses.BOMMeterBus;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

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


    @Inject
    private BOMMeterBus bomMetersBus;

    @Inject
    private HESClient hesClient;



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

    public void refresh() {

    }

    public void initiateAll() {
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

    public void fetchBomMeters(Long bomId) {
        this.bomId = bomId;
        metersElectricity = bomMetersBus.findAllByBomIdForElectricType(bomId);
        metersWater = bomMetersBus.findAllByBomIdForWaterType(bomId);
    }

    public void fetchBomMetersWithMeterModel(Long bomId) {
        this.bomId = bomId;
        metersElectricity = bomMetersBus.findAllByBomIdForElectricType(bomId);
        metersWater = bomMetersBus.findAllByBomIdForWaterType(bomId);
        meters = new ArrayList<>();
        meters.addAll(metersWater);
        meters.addAll(metersElectricity);
        //find all the meters which are not mapped to a gateway - METER_GTW_ID is null
//        metersTarget = new ArrayList<>();
//        metersTarget.addAll(meters.stream().filter(meter -> null == meter.getMeterGtwId()).collect(
//                Collectors.toList()));
        //find all the meters which are already mapped to a gateway - METER_GTW_ID is not null
        //metersMapped = new ArrayList<>();
    }

    public String getTotalMeters() {
        Integer totalMeters = 0;
        if (metersElectricity != null && metersWater != null) {
            totalMeters = metersElectricity.size() + metersWater.size();
        }
        return totalMeters.toString();
    }

    public void addNewMeter(){

        String errormsg = "New Meter Added Successfully";
        FacesMessage msg = null;
        /*
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

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success",errormsg));
        }
        */
        hesClient.GetAllDevices();
    }

    public void addNewBOMWM(){
        metersWater = bomMetersBus.addNewMeterByBomId(bomId, "WATER", wmeterManufacturer, wmeterSerial, wmeterModel, wmeterType);

    }

    public void addNewBOMEM() {
        metersElectricity = bomMetersBus.addNewMeterByBomId(bomId, "ELECTRIC", emeterManufacturer, emeterSerial, emeterModel, emeterType);

    }

    public void deleteBOMWM(Long meterID) {
        if (meterID != null) {
            metersWater = bomMetersBus.deleteMeter(meterID, bomId, "WATER");
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Failure", "New Work Order Initiated WO: "));
        }

    }

    public void deleteBOMEM(Long meterID) {
        if (meterID != null) {
            metersElectricity = bomMetersBus.deleteMeter(meterID, bomId, "ELECTRIC");
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Failure", "Failed to delete requested meter: "));
        }

    }

    public void saveBOMEM(BOMMeterDTO emtr) {
        emtr.setBomMeterType("ELECTRIC");
        bomMetersBus.updateMeterDetails(emtr);
    }

    public void saveBOMWM(BOMMeterDTO wmtr) {
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

    /******/

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
            this.metersSource = this.meters.stream().filter(meter -> (null == meter.getMeterGtwId()))
                    .collect(Collectors.toList());
            //target should be all meters with an association with the given gateway
            this.metersTarget = this.meters.stream().filter(meter -> (gatewayId.equals(meter.getMeterGtwId())))
                    .collect(Collectors.toList());
        }
        this.getBomMeterModel();
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

    public void onUtilityChange(){

    }

    public void connectionTest(){
        String test = "Test";
        hesClient.GetAllDevices();
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        List<BOMMeterDTO> metersToTarget, metersToSource = null;
        DualListModel model = getBomMeterModel();
        if (event.isAdd()) {
            metersToTarget = (List<BOMMeterDTO>) event.getItems();
            for (BOMMeterDTO item : metersToTarget) {
                builder.append(item.getId()).append(File.separator);
            }
            this.metersSource.removeAll(metersToTarget);
            this.metersTarget.addAll(metersToTarget);
        } else {
            metersToSource = (List<BOMMeterDTO>) event.getItems();
            for (BOMMeterDTO item : metersToSource) {
                builder.append(item.getId()).append(File.separator);
            }
            this.metersSource.addAll(metersToSource);
            this.metersTarget.removeAll(metersToSource);
        }
        model.setSource(this.metersTarget);
        model.setTarget(this.metersSource);
        LOGGER.info("Items transferred " + builder.toString());
        /*FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
    }

    public void updateGtwBomMeterMapping(BigInteger gtwId) {
        if (this.metersTarget == null && this.metersTarget == null) {
            return;
        }
        int i;
        for (BOMMeterDTO meterDTO : this.metersTarget) {
            i = meters.indexOf(meterDTO);
            if (i >= 0) {
                ((BOMMeterDTO) meters.get(i)).setMeterGtwId(gtwId);
            }
        }
        for (BOMMeterDTO meterDTO : this.metersSource) {
            i = meters.indexOf(meterDTO);
            if (i >= 0) {
                ((BOMMeterDTO) meters.get(i)).setMeterGtwId(null);
            }
        }
        FacesMessage msg;
        try {
            bomMetersBus.updateMeterAll(this.metersSource, this.metersTarget);
            for (BOMMeterDTO meterDTO : this.metersTarget) {
                i = meters.indexOf(meterDTO);
                if (i >= 0) {
                    ((BOMMeterDTO) meters.get(i)).setMeterGtwId(gtwId);
                }
            }
            for (BOMMeterDTO meterDTO : this.metersSource) {
                i = meters.indexOf(meterDTO);
                if (i >= 0) {
                    ((BOMMeterDTO) meters.get(i)).setMeterGtwId(null);
                }
            }
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Gateway - Meter mapping completed for gateway with id -> " + gtwId);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error during Gateway - Meter mapping for gateway with id " + gtwId);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            LOGGER.error("Exception during sql update", e);
        }
    }

}
