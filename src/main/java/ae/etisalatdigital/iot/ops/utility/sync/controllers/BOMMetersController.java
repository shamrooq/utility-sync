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
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
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
@ViewScoped
public class BOMMetersController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(BOMMetersController.class);

    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private BOMMeterDTO meterRecord;

    private Long bomId;
    private String utilityNumber;
    private String errorMessage;

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
    List<BOMMeterDTO> metersSource;
    List<BOMMeterDTO> metersTarget;
    private DualListModel<BOMMeterDTO> bomMeterModel;

    public BOMMetersController() {
        metersSource = new ArrayList<>();
        metersTarget = new ArrayList<>();
        this.bomMeterModel = new DualListModel<>(metersTarget, metersSource);
    }

    @PostConstruct
    public void init() {
        metersSource = new ArrayList<>();
        metersTarget = new ArrayList<>();
        this.bomMeterModel = new DualListModel<>(metersTarget, metersSource);
    }
    @Inject
    private BOMMeterBus bomMetersBus;

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

    public void addNewBOMWM() {
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
