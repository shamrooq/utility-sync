/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans;

import java.io.Serializable;
import java.util.UUID;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

/**
 *
 * @author appadmin
 */
@Named
@ViewScoped
public class MindmapView implements Serializable {

    private MindmapNode root;

    private MindmapNode selectedNode;

    public MindmapView() {
        root = new DefaultMindmapNode("Vendor Selection", "Google WebSite", "FFCC00", false);

        MindmapNode ips = new DefaultMindmapNode("Territory Assignement", "Territory Assignement", "6e9ebf", true);
        MindmapNode ns = new DefaultMindmapNode("Metering Infrastructure", "Metering Infrastructure", "6e9ebf", true);
        MindmapNode malware = new DefaultMindmapNode("Gatway Selection", "Gatway Selection", "3399ff", true);

        MindmapNode mlPredict = new DefaultMindmapNode("ML Prediction", "US ML Prediction", "6e9ebf", true);
        MindmapNode surveyor = new DefaultMindmapNode("Surveyor Discretion", "US ML Prediction", "6e9ebf", true);
        
        malware.addNode(surveyor);
        malware.addNode(mlPredict);
        
        root.addNode(ips);
        root.addNode(ns);
        root.addNode(malware);
        
        
        
        
    }

    public MindmapNode getRoot() {
        return root;
    }

    public MindmapNode getSelectedNode() {
        return selectedNode;
    }
    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }
    
    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();

        //populate if not already loaded
        if(node.getChildren().isEmpty()) {
            Object label = node.getLabel();

            if(label.equals("NS(s)")) {
                for(int i = 0; i < 25; i++) {
                    node.addNode(new DefaultMindmapNode("ns" + i + ".google.com", "Namespace " + i + " of Google", "82c542", false));
                }
            }
            else if(label.equals("IPs")) {
                for(int i = 0; i < 18; i++) {
                    node.addNode(new DefaultMindmapNode("1.1.1."  + i, "IP Number: 1.1.1." + i, "fce24f", false));
                }
            }
            else if(label.equals("ML Prediction")) {
                for(int i = 0; i < 18; i++) {
                    String random = UUID.randomUUID().toString();
                    node.addNode(new DefaultMindmapNode("ML Scenario-"  + random, "US Scenarios: " + random, "3399ff", false));
                }
            }
        }
    }
    
    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();
    }

}