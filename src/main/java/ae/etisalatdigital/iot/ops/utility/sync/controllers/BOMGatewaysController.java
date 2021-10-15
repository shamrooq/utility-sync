/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.controllers;

import ae.etisalatdigital.iot.ops.utility.sync.buses.BOMGatewayEstBus;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;

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
    private String utilityNumber;
    private Long bomId;
    private String errorMessage;
    private String gatewaysType;
    private String gatewaysTypeProposed;
    private int gatewaysRequired;
    private int metersPerGateway;
    private String estimatedCableLength;
    private String gatewaysVendor;
    private String gatewaysLocation;

    private String gatewaysDaisychain;
    private String gatewaysChainLabel;
    private List<BOMGatewayEstDTO> estimation;
    private int rowsPerPage = 10;
    @Inject
    private BOMGatewayEstBus gatewayEstBus;

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

    public void updateEstimation(Long bomId) {
        this.bomId = bomId;
        estimation = gatewayEstBus.findAllByBomId(bomId);
        if (estimation == null) {
            List<BOMGatewayEstDTO> list = new ArrayList<>();
            estimation = list;
        }
        setRowsPerPage(estimation.size());
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

    public void addNewGatewayEst() {
        gatewaysRequired = 1;
        estimation = gatewayEstBus.addNewGatewayEstByBomId(bomId, gatewaysType, gatewaysTypeProposed, gatewaysRequired, metersPerGateway, estimatedCableLength, gatewaysLocation);
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

    public BOMGatewayEstDTO saveGatewayEstimation(BOMGatewayEstDTO gatewayItem) {
        LOGGER.info("BOMGatewayEstDTO.saveGatewayEstimation called");
        gatewayEstBus.updateGatewayDetails(gatewayItem);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Details successfully updated for gateway with id -> "+
                gatewayItem.getId()));
        return gatewayItem;
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

    public String getEstimatedCableLength() {
        return estimatedCableLength;
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

    public void setEstimatedCableLength(String estimatedCableLength) {
        this.estimatedCableLength = estimatedCableLength;
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

    public int getRowsPerPage() {
        return rowsPerPage;
    }
    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }
}
