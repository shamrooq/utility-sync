/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author appadmin
 */
@Entity
@Table(name = "MST_Gateway_Type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTGatewayTypes.findAll", query = "SELECT m FROM MSTGatewayTypes m")
})
public class MSTGatewayTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GATEWAY_TYPE_ID", nullable = false)
    private Long id;
    
    @Column(name = "GATEWAY_TYPE_Code", length = 10)
    private String gatewayTypeCode;
    
    @Column(name = "GATEWAY_TYPE_Name", length = 200)
    private String gatewayTypeName;
    
    @Column(name = "GATEWAY_TYPE_Description", length = 300)
    private String gatewayTypeDescription;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public Long getId() {
        return id;
    }

    public String getGatewayTypeCode() {
        return gatewayTypeCode;
    }

    public String getGatewayTypeName() {
        return gatewayTypeName;
    }

    public String getGatewayTypeDescription() {
        return gatewayTypeDescription;
    }
    
    

    public void setId(Long id) {
        this.id = id;
    }

    public void setPremiseTypeCode(String gatewayTypeCode) {
        this.gatewayTypeCode = gatewayTypeCode;
    }

    public void setPremiseTypeName(String gatewayTypeName) {
        this.gatewayTypeName = gatewayTypeName;
    }

    public void setPremiseTypeDescription(String gatewayTypeDescription) {
        this.gatewayTypeDescription = gatewayTypeDescription;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSTGatewayTypes)) {
            return false;
        }
        MSTGatewayTypes other = (MSTGatewayTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.MSTGatewayTypes[ id=" + id + " ]";
    }
    
}
