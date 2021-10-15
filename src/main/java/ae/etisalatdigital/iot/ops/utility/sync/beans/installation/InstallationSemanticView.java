/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans.installation;

import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.organigram.OrganigramHelper;
import org.primefaces.event.organigram.OrganigramNodeCollapseEvent;
import org.primefaces.event.organigram.OrganigramNodeDragDropEvent;
import org.primefaces.event.organigram.OrganigramNodeExpandEvent;
import org.primefaces.event.organigram.OrganigramNodeSelectEvent;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;

/**
 *
 * @author appadmin
 */
@Named(value = "installationSemanticView")
@ViewScoped
public class InstallationSemanticView implements Serializable {

    private OrganigramNode rootNode;
    private OrganigramNode selection;
    private static final String HYPHEN_STR = " - ";
    private boolean zoom = false;
    private String style = "width: 1200px";
    private int leafNodeConnectorHeight = 12;
    private boolean autoScrollToSelection = false;
    private String utilityNumber;
    private String premiseType;
    private String meterName;
    private UtilityGatewayMeterSemantics semantics;
    private Map<String,BOMGatewayEstDTO> gatewayMap;
    private Map<String,BOMMeterDTO> meterMap;
    private BOMGatewayEstDTO gateway;
    private BOMMeterDTO meter;
    public InstallationSemanticView() {
    }

    public InstallationSemanticView(String utilityNumber, String premiseType) {
        this.utilityNumber = utilityNumber;
        this.premiseType = premiseType == null ? "root" : premiseType.toLowerCase();
        this.rootNode = new DefaultOrganigramNode(this.premiseType,
                utilityNumber, null);
        this.rootNode.setCollapsible(true);
        this.rootNode.setDroppable(true);
    }

    public UtilityGatewayMeterSemantics getGtwMtrList() {
        return semantics;
    }

    public void setGtwMtrList(UtilityGatewayMeterSemantics gtwMtrList) {
        this.semantics = gtwMtrList;
    }

    public String getPremiseType() {
        return premiseType;
    }

    public void setPremiseType(String premiseType) {
        this.premiseType = premiseType;
    }

    public void init() {
        selection = rootNode;
        gatewayMap= new HashMap<>();
        meterMap = new HashMap<>();
        for (BOMGatewayEstDTO gtwNode : this.semantics.getGatewaySet()) {
            addDivision(rootNode, gtwNode,
                    this.semantics.getGtwMeterMap().get(gtwNode.getId()));
        }
    }

    public String getUtilityNumber() {
        return utilityNumber;
    }

    public void setUtilityNumber(String utilityNumber) {
        this.utilityNumber = utilityNumber;
    }

    protected OrganigramNode addDivision(OrganigramNode parent, BOMGatewayEstDTO gtwNode, Set<BOMMeterDTO> meters) {
        OrganigramNode divisionNode = new DefaultOrganigramNode("gateway", gtwNode.getSerialNumber(), parent);
        gatewayMap.put(gtwNode.getSerialNumber(), gtwNode);
        //divisionNode.setRowKey(gtwNode.getGatewayFloor() + InstallationSemanticView.HYPHEN_STR + gtwNode.getGatewayRoom());
        divisionNode.setDroppable(true);
        divisionNode.setDraggable(true);
        divisionNode.setSelectable(true);
        divisionNode.setExpanded(false);
        if (meters != null) {
            for (BOMMeterDTO meter : meters) {
                String type = null == meter.getBomMeterType() ? "water" : meter.getBomMeterType().toLowerCase();
                OrganigramNode meterNode = new DefaultOrganigramNode(type,
                        meter.getMeterSerial(), divisionNode);
                /*meterNode.setRowKey(meter.getMeterFloor() + InstallationSemanticView.HYPHEN_STR + meter.getMeterRoom()
                        + meter.getMeterManufacturer() + InstallationSemanticView.HYPHEN_STR + meter.getMeterModel()
                        + InstallationSemanticView.HYPHEN_STR + meter.getMeterSerial());*/
                meterMap.put(meter.getMeterSerial(), meter);
                meterNode.setDraggable(true);
                meterNode.setSelectable(true);
            }
        }
        return divisionNode;
    }

    public void nodeDragDropListener(OrganigramNodeDragDropEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() + "' moved from " + event.getSourceOrganigramNode().getData() + " to '" + event.getTargetOrganigramNode().getData() + "'");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void nodeSelectListener(OrganigramNodeSelectEvent event) {
        String detail;
        switch(event.getOrganigramNode().getType()){
            case "gateway":
                detail = "Gateway -> "+this.getNodeGateway(event.getOrganigramNode().getData().toString());
                break;
            case "water":
                detail = "Water Meter -> "+this.getNodeMeter(event.getOrganigramNode().getData().toString());
                break;
            case "electric":
                detail = "Electric Meter -> "+this.getNodeMeter(event.getOrganigramNode().getData().toString());
                break;
            default:
                detail = event.getOrganigramNode().getType();
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,event.getOrganigramNode().
                getData().toString(),detail);
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), message);
    }

    public void nodeCollapseListener(OrganigramNodeCollapseEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() + "' collapsed.");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void nodeExpandListener(OrganigramNodeExpandEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() + "' expanded.");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void removeDivision() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        setMessage(currentSelection.getData() + " will entfernt werden.", FacesMessage.SEVERITY_INFO);
    }

    public void removeMeter() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        currentSelection.getParent().getChildren().remove(currentSelection);
    }

    public void addMeter() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        OrganigramNode employee = new DefaultOrganigramNode("meter", meterName, currentSelection);
        employee.setDraggable(true);
        employee.setSelectable(true);
    }

    private void setMessage(String msg, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage();
        message.setSummary(msg);
        message.setSeverity(severity);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public OrganigramNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(OrganigramNode rootNode) {
        this.rootNode = rootNode;
    }

    public OrganigramNode getSelection() {
        return selection;
    }

    public void setSelection(OrganigramNode selection) {
        this.selection = selection;
    }

    public boolean isZoom() {
        return zoom;
    }

    public void setZoom(boolean zoom) {
        this.zoom = zoom;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setEmployeeName(String meterName) {
        this.meterName = meterName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getLeafNodeConnectorHeight() {
        return leafNodeConnectorHeight;
    }

    public void setLeafNodeConnectorHeight(int leafNodeConnectorHeight) {
        this.leafNodeConnectorHeight = leafNodeConnectorHeight;
    }

    public boolean isAutoScrollToSelection() {
        return autoScrollToSelection;
    }

    public void setAutoScrollToSelection(boolean autoScrollToSelection) {
        this.autoScrollToSelection = autoScrollToSelection;
    }

    public Map<String, BOMGatewayEstDTO> getGatewayMap() {
        return gatewayMap;
    }

    public void setGatewayMap(Map<String, BOMGatewayEstDTO> gatewayMap) {
        this.gatewayMap = gatewayMap;
    }

    public Map<String, BOMMeterDTO> getMeterMap() {
        return meterMap;
    }

    public void setMeterMap(Map<String, BOMMeterDTO> meterMap) {
        this.meterMap = meterMap;
    }
    public String getNodeGateway(String key) {
        this.gateway = (BOMGatewayEstDTO)gatewayMap.get(key);
        return gateway.getGatewayFloor() + InstallationSemanticView.HYPHEN_STR+gateway.getGatewayRoom();
    }

    public String getNodeMeter(String key) {
        this.meter = (BOMMeterDTO)meterMap.get(key);
        return this.meter.getMeterManufacturer() + InstallationSemanticView.HYPHEN_STR+meter.getMeterModel()+
                InstallationSemanticView.HYPHEN_STR+meter.getMeterFloor()+InstallationSemanticView.HYPHEN_STR+
                meter.getMeterRoom();
    }

}
