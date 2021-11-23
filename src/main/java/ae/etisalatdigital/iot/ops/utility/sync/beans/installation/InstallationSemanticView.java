/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans.installation;

import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTFloor;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.organigram.OrganigramNodeCollapseEvent;
import org.primefaces.event.organigram.OrganigramNodeDragDropEvent;
import org.primefaces.event.organigram.OrganigramNodeExpandEvent;
import org.primefaces.event.organigram.OrganigramNodeSelectEvent;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.OrganigramNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.LabelOverlay;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import static ae.etisalatdigital.iot.ops.utility.sync.util.UtilityConstants.*;

/**
 *
 * @author appadmin
 */
@Named
@ViewScoped
public class InstallationSemanticView implements Serializable {

    //private OrganigramNode rootNode;
    private TreeNode rootNode;
    //private OrganigramNode selection;
    private TreeNode selection;
    private boolean zoom = false;
    private String style = "width: 1200px";
    private int leafNodeConnectorHeight = 12;
    private boolean autoScrollToSelection = false;
    private String utilityNumber;
    private String premiseType;
    private String meterName;
    private UtilityGatewayMeterSemantics semantics;
    private Map<String,DefaultDiagramModel> floorDiagramMap;
    private Map<String,BOMGatewayEstDTO> gatewayMap;
    private Map<String,BOMMeterDTO> meterMap;
    private BOMGatewayEstDTO gateway;
    private BOMMeterDTO meter;
    //private InstallationSemanticsTreeView view;
    private DefaultDiagramModel model;
    private final int gxCoordinate=40;
    private final int gyCoordinate=15;
    private int mxCoordinate=gxCoordinate+100;
    private int myCoordinate=-gyCoordinate;

    public InstallationSemanticView() {
    }

    /*public InstallationSemanticView(String utilityNumber, String premiseType) {
        this.utilityNumber = utilityNumber;
        this.premiseType = premiseType == null ? "root" : premiseType.toLowerCase();
        this.rootNode = new DefaultOrganigramNode(this.premiseType,
                utilityNumber, null);
        this.rootNode.setCollapsible(true);
        this.rootNode.setDroppable(true);
    }*/

    public InstallationSemanticView(String utilityNumber, String premiseType) {
        this.utilityNumber = utilityNumber;
        this.premiseType = premiseType == null ? "root" : premiseType.toLowerCase();
        //view = new InstallationSemanticsTreeView(this.premiseType,utilityNumber,this.premiseType);
        this.rootNode = new DefaultTreeNode(this.premiseType,utilityNumber, null);
        this.rootNode.setExpanded(Boolean.TRUE);
        this.rootNode.setSelectable(Boolean.TRUE);
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

    /*public void init() {
        selection = rootNode;
        gatewayMap= new HashMap<>();
        meterMap = new HashMap<>();
        for(Map.Entry<Long, Set<BOMGatewayEstDTO>> entry:this.semantics.getGatewayFloors().entrySet()){
            BOMGatewayEstDTO firstElement = (BOMGatewayEstDTO)((TreeSet)entry.getValue()).first();
            addMeterNodes(rootNode, firstElement.getGatewayFloor(),
                    entry.getValue());
        }
    }*/

    public void init() {
        selection = rootNode;
        gatewayMap= new HashMap<>();
        meterMap = new HashMap<>();
        floorDiagramMap = new HashMap<>();
        for(Map.Entry<Long, Set<BOMGatewayEstDTO>> entry : this.semantics.getGatewayFloors().entrySet()){
            BOMGatewayEstDTO firstElement = ((TreeSet<BOMGatewayEstDTO>)entry.getValue()).first();
            addFloorNodes(this.rootNode, firstElement.getGatewayFloor(),
                    entry.getValue());
        }
    }

    public String getUtilityNumber() {
        return utilityNumber;
    }

    public void setUtilityNumber(String utilityNumber) {
        this.utilityNumber = utilityNumber;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }
    public DefaultDiagramModel getModel() {
        return model;
    }

    /**
     * method to add all the floors and gateway linked to each floor of a utility reference number(parent)
     * @param parent
     * @param floorCode
     * @param gatewayEstDTOList
     */
    protected void addFloorAndGtwNodes(OrganigramNode parent, String floorCode, Set<BOMGatewayEstDTO> gatewayEstDTOList) {
        OrganigramNode divisionNode = new DefaultOrganigramNode(FLR_NODE_NAME,
                floorCode, parent);
        divisionNode.setDroppable(true);
        divisionNode.setDraggable(true);
        divisionNode.setSelectable(true);
        divisionNode.setExpanded(false);
        if (gatewayEstDTOList != null) {
            for (BOMGatewayEstDTO gtw : gatewayEstDTOList) {
                OrganigramNode gatewayNode = new DefaultOrganigramNode(GTW_NODE_NAME,
                        gtw.getSerialNumber(), divisionNode);
                gatewayNode.setExpanded(false);
                gatewayNode.setDraggable(true);
                gatewayNode.setSelectable(true);
                addFloorAndGtwNodes(gatewayNode,gtw,this.semantics.getGtwMeterMap().get(gtw.getId()));
            }
        }
    }

    /**
     * add all the floors associated with a Utility ref number to the tree
     * @param parent
     * @param floorCode
     * @param gatewayEstDTOList
     */
    protected void addFloorNodes(TreeNode parent, String floorCode, Set<BOMGatewayEstDTO> gatewayEstDTOList) {
        String data="";
        MSTFloor mstfloor=this.semantics.getFloorMap().get(floorCode);
        if(mstfloor!=null){
            data = mstfloor.getFloorCode();
        }
        TreeNode floorNode = new DefaultTreeNode(FLR_NODE_NAME, data, parent);
        floorNode.setSelectable(Boolean.TRUE);
        floorNode.setExpanded(Boolean.TRUE);
        if (gatewayEstDTOList != null) {
            DefaultDiagramModel diagram = new DefaultDiagramModel();
            gatewayEstDTOList.forEach(gtw -> addGatewayAndMeterNodes(diagram, gtw));
            floorDiagramMap.put(floorCode,diagram);
        }
    }

    public DefaultDiagramModel getFloorElements(String floorCode){
        this.model = this.floorDiagramMap.get(floorCode);
        return this.model;
    }

    protected void addGatewayAndMeterNodes(DefaultDiagramModel diagram, BOMGatewayEstDTO gtw){
        diagram.setMaxConnections(-1);
        Element element = new Element(gtw.getSerialNumber(),gxCoordinate+DIAGRAM_PXL_SIZE_UNIT,gyCoordinate+DIAGRAM_PXL_SIZE_UNIT);
        diagram.addElement(element);
        element.setDraggable(false);
        element.setStyleClass("diagram-gateway-box");
        element.setTitle("Gateway "+(null==gtw.getGatewaysVendor()?"":HYPHEN_STR_SPACE+gtw.getGatewaysVendor())+(null==gtw.getGatewaysChainLabel()?"":HYPHEN_STR_SPACE+gtw.getGatewaysChainLabel()));
        gatewayMap.put(gtw.getSerialNumber(), gtw);
        mxCoordinate=gxCoordinate+100;
        addMeterNodes(diagram,element,gtw.getBomMeterList());
    }
    /**
     * method to add each meterDto node to its parent gateway node
     * @param parent
     * @param gtwNode
     * @param meters
     */
    protected void addFloorAndGtwNodes(OrganigramNode parent, BOMGatewayEstDTO gtwNode, Set<BOMMeterDTO> meters) {
        gatewayMap.put(gtwNode.getSerialNumber(), gtwNode);
        if (meters != null) {
            for (BOMMeterDTO meterDto : meters) {
                String type = null == meterDto.getBomMeterType() ? WTR_MTR_NODE_NAME : meterDto.getBomMeterType().toLowerCase();
                OrganigramNode meterNode = new DefaultOrganigramNode(type,
                        meterDto.getMeterSerial(), parent);
                meterMap.put(meterDto.getMeterSerial(), meterDto);
                meterNode.setDraggable(true);
                meterNode.setSelectable(true);
            }
        }
    }

    /**
     * method to add each meterDto node to its parent gateway node
     * @param parent
     * @param gtwNode
     * @param meters
     */
    protected void addMeterNodes(TreeNode parent, BOMGatewayEstDTO gtwNode, Set<BOMMeterDTO> meters) {
        gatewayMap.put(gtwNode.getSerialNumber(), gtwNode);
        if (meters != null) {
            for (BOMMeterDTO meterDto : meters) {
                String type = null == meterDto.getBomMeterType() ? WTR_MTR_NODE_NAME : meterDto.getBomMeterType().toLowerCase();
                /*view = new InstallationSemanticsTreeView(meterDto.getMeterSerial(),
                        meterDto.getBomMeterType()+HYPHEN_STR+meterDto.getMeterManufacturer()+HYPHEN_STR+meterDto.getMeterModel()+HYPHEN_STR+meterDto.getMeterRoom(),type);*/
                TreeNode meterNode = new DefaultTreeNode(type,
                        meterDto.getMeterSerial(), parent);
                //System.out.println(" meter :"+meterNode.getData());
                meterMap.put(meterDto.getMeterSerial(), meterDto);
                meterNode.setSelectable(true);
            }
        }
    }

    protected void addMeterNodes(DefaultDiagramModel diagram, Element parent, Set<BOMMeterDTO> meters) {
        if (meters != null) {
            for(BOMMeterDTO meterDto : meters){
                String className = "diagram-meter-water-box";
                meterMap.put(meterDto.getMeterSerial(), meterDto);
                Element element = new Element(meterDto.getMeterSerial(), mxCoordinate + DIAGRAM_PXL_SIZE_UNIT, myCoordinate + DIAGRAM_PXL_SIZE_UNIT);
                myCoordinate += 15;
                if (ELECTRIC_METER_STR.equalsIgnoreCase(meterDto.getBomMeterType())) {
                    className = "diagram-meter-electric-box";
                }
                element.setStyleClass(className);
                element.setDraggable(Boolean.TRUE);
                diagram.addElement(element);
                //appending a dot at right of gateway node
                DotEndPoint p1 = new DotEndPoint();
                p1.setAnchor(EndPointAnchor.RIGHT);
                p1.setRadius(4);
                parent.addEndPoint(p1);
                //appending a dot at the left of meter node
                DotEndPoint p2 = new DotEndPoint();
                p2.setAnchor(EndPointAnchor.LEFT);
                p2.setRadius(4);
                element.addEndPoint(p2);
                StraightConnector ctr = new StraightConnector(4, 8);
                Connection c = new Connection(p1, p2, ctr);
                String label = null == meterDto.getMeterLabelCBL() ? (meterDto.getMeterManufacturer() == null ? "" : meterDto.getMeterManufacturer()) : meterDto.getMeterLabelCBL();
                c.getOverlays().add(new LabelOverlay(label));
                c.setConnector(ctr);
                c.setDetachable(Boolean.TRUE);
                diagram.connect(c);
            }
        }
    }

    public void nodeDragDropListener(OrganigramNodeDragDropEvent event) {
        addMessage(FacesMessage.SEVERITY_INFO, "Node '" + event.getOrganigramNode().getData() + "' moved from " +
                        event.getSourceOrganigramNode().getData() + " to '" + event.getTargetOrganigramNode().getData() + "'",
                null, null);
    }

    public void nodeSelectListener(OrganigramNodeSelectEvent event) {
        String detail;
        switch(event.getOrganigramNode().getType()){
            case GTW_NODE_NAME:
                detail = "Gateway -> "+this.getNodeGateway(event.getOrganigramNode().getData().toString());
                break;
            case WTR_MTR_NODE_NAME:
                detail = "Water Meter -> "+this.getNodeMeter(event.getOrganigramNode().getData().toString());
                break;
            case ETC_MTR_NODE_NAME:
                detail = "Electric Meter -> "+this.getNodeMeter(event.getOrganigramNode().getData().toString());
                break;
            default:
                detail = event.getOrganigramNode().getType();
        }
        addMessage(FacesMessage.SEVERITY_INFO, event.getOrganigramNode().
                getData().toString(),detail,event.getComponent().getClientId());
    }

    public void onNodeSelect(NodeSelectEvent event) {
        String detail;
        switch(event.getTreeNode().getType()){
            case GTW_NODE_NAME:
                detail = "Gateway -> "+this.getNodeGateway(event.getTreeNode().getData().toString());
                break;
            case WTR_MTR_NODE_NAME:
                detail = "Water Meter -> "+this.getNodeMeter(event.getTreeNode().getData().toString());
                break;
            case ETC_MTR_NODE_NAME:
                detail = "Electric Meter -> "+this.getNodeMeter(event.getTreeNode().getData().toString());
                break;
            default:
                detail = event.getTreeNode().getType();
        }
        addMessage(FacesMessage.SEVERITY_INFO, event.getTreeNode().
                getData().toString(),detail,null);
    }

    public void nodeCollapseListener(OrganigramNodeCollapseEvent event) {
        addMessage(FacesMessage.SEVERITY_INFO, "Node '" + event.getOrganigramNode().getData() + "' collapsed.", null, null);
    }

    public void onNodeCollapse(NodeCollapseEvent event) {
        addMessage(FacesMessage.SEVERITY_INFO, "Node '" + event.getTreeNode().getData() + "' collapsed.", null, null);
    }

    public void nodeExpandListener(OrganigramNodeExpandEvent event) {
        addMessage(FacesMessage.SEVERITY_INFO, "Node '" + event.getOrganigramNode().getData() + "' expanded.", null, null);
    }

    public void onNodeExpand(NodeExpandEvent event) {
        addMessage(FacesMessage.SEVERITY_INFO, "Node '" + event.getTreeNode().getData() + "' expanded.", null, null);
    }

    /*public void removeDivision() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        addMessage(currentSelection.getData() + " will entfernt werden.", FacesMessage.SEVERITY_INFO);
    }*/

    

    /*public void removeMeter() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        currentSelection.getParent().getChildren().remove(currentSelection);
    }*/

    /*public void addMeter() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        OrganigramNode employee = new DefaultOrganigramNode("meterDto", meterName, currentSelection);
        employee.setDraggable(true);
        employee.setSelectable(true);
    }*/

    private void addMessage(FacesMessage.Severity severity,String msg, String detail,String clientId) {
        FacesMessage message = new FacesMessage(severity,msg,detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /*
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
    */

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public TreeNode getSelection() {
        return selection;
    }

    public void setSelection(TreeNode selection) {
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
        this.gateway = gatewayMap.get(key);
        return gateway.getGatewayFloor() + HYPHEN_STR_SPACE+gateway.getGatewayRoom();
    }

    public String getFloorNodeDetails(String key) {
        MSTFloor optionalFloor = Optional.ofNullable(this.semantics.getFloorMap().get(key)).orElse(null);
        return optionalFloor == null?"":optionalFloor.getFloorDescription();
    }

    public String getNodeMeter(String key) {
        this.meter = meterMap.get(key);
        return this.meter.getMeterManufacturer() + HYPHEN_STR_SPACE+meter.getMeterModel()+
                HYPHEN_STR_SPACE+meter.getMeterFloor()+HYPHEN_STR_SPACE+
                meter.getMeterRoom();
    }

}
