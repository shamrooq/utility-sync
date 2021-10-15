/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author au_mobility
 */
@Entity
@Table(name = "MST_Emirate_Region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTEmirateRegions.findAll", query = "SELECT m FROM MSTEmirateRegions m")
})
public class MSTEmirateRegions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EREGION_ID", nullable = false)
    private Long id;
    
    @Column(name = "EREGION_Code", length = 10)
    private String emirateRegionCode;
    
    @Column(name = "EREGION_Name", length = 200)
    private String emirateRegionName;
    
    @Column(name = "EREGION_Description", length = 300)
    private String emirateRegionDescription;
    
    @Column(name = "Created_Date")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date createdDate;
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @Column(name = "Is_Disable")
    private Boolean isDisabled;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public Long getId() {
        return id;
    }

    public String getEmirateRegionCode() {
        return emirateRegionCode;
    }

    public String getEmirateRegionName() {
        return emirateRegionName;
    }

    public String getEmirateRegionDescription() {
        return emirateRegionDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Boolean getIsDisabled() {
        return isDisabled;
    }
    
    /**
     * 
     * @param id 
     */

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmirateRegionCode(String emirateRegionCode) {
        this.emirateRegionCode = emirateRegionCode;
    }

    public void setEmirateRegionName(String emirateRegionName) {
        this.emirateRegionName = emirateRegionName;
    }

    public void setEmirateRegionDescription(String emirateRegionDescription) {
        this.emirateRegionDescription = emirateRegionDescription;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
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
        if (!(object instanceof MSTEmirateRegions)) {
            return false;
        }
        MSTEmirateRegions other = (MSTEmirateRegions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.MSTEmirateRegions[ id=" + id + " ]";
    }
    
}
