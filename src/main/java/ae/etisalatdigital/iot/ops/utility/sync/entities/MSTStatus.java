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
 * @author au_addc
 */
@Entity
@Table(name = "MST_Status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTStatus.findAll", query = "SELECT m FROM MSTStatus m")
})
public class MSTStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STATUS_ID", nullable = false)
    private Long id;

    
    @Column(name = "STATUS_Code", length = 50)
    private String statusCode;
    
    @Column(name = "STATUS_Stage", length = 100)
    private String statusStage;
    
    @Column(name = "STATUS_Module", length = 100)
    private String statusModule;
    
    @Column(name = "STATUS_Description", length = 300)
    private String statusDescription;
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public Long getId() {
        return id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusStage() {
        return statusStage;
    }

    public String getStatusModule() {
        return statusModule;
    }

    public String getStatusDescription() {
        return statusDescription;
    }
    
    

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusStage(String statusStage) {
        this.statusStage = statusStage;
    }

    public void setStatusModule(String statusModule) {
        this.statusModule = statusModule;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
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
        if (!(object instanceof MSTStatus)) {
            return false;
        }
        MSTStatus other = (MSTStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.MSTStatus[ id=" + id + " ]";
    }
    
}
