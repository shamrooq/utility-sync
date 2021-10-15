/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author appadmin
 */
@Named("treeBasicView")
@ViewScoped
public class TreeBasicView implements Serializable {

    private TreeNode root;

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Vendor", null);
        TreeNode node0 = new DefaultTreeNode("In-House", root);
        TreeNode node1 = new DefaultTreeNode("Telephony", root);
        TreeNode node2 = new DefaultTreeNode("ARC - Al Rostamani", root);

        
        TreeNode node10 = new DefaultTreeNode("Water Gateway", node1);
        TreeNode node11 = new DefaultTreeNode("Electricity Gateway", node1);
        TreeNode node12 = new DefaultTreeNode("Hybrid", node1);

        TreeNode node20 = new DefaultTreeNode("Water Gateway", node2);
        TreeNode node21 = new DefaultTreeNode("Electricity Gateway", node2);
        TreeNode node22 = new DefaultTreeNode("Hybrid", node2);
        
        
        node10.getChildren().add(new DefaultTreeNode("1-1 Water Meter Battery"));
        node11.getChildren().add(new DefaultTreeNode("1-1 Meter DC"));
        node11.getChildren().add(new DefaultTreeNode("1-2 Meter DC"));
        node11.getChildren().add(new DefaultTreeNode("1-32 Meter DC"));
        node11.getChildren().add(new DefaultTreeNode("1-64 Meter DC"));
        
        node20.getChildren().add(new DefaultTreeNode("WR222-NB"));
        

        node21.getChildren().add(new DefaultTreeNode("SCB270-DL-NB-DC"));
        node21.getChildren().add(new DefaultTreeNode("SCB211-DL-NB-DC"));
        node21.getChildren().add(new DefaultTreeNode("SCB111-MB-NB-DC"));
        node21.getChildren().add(new DefaultTreeNode("SCB270-MB-NB-DC"));
        node21.getChildren().add(new DefaultTreeNode("SCB111-URDS-NB-DC"));
        
	}

    public TreeNode getRoot() {
        return root;
    }
    
}
