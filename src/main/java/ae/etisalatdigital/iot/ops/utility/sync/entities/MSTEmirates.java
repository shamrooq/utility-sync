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
@Table(name = "MST_Emirate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTEmirates.findAll", query = "SELECT m FROM MSTEmirates m")
})
public class MSTEmirates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMIRATE_ID", nullable = false)
    private Long id;
    
    @Column(name = "EMIRATE_Code", length = 10)
    private String emirateCode;
    
    @Column(name = "EMIRATE_Name", length = 200)
    private String emirateName;
    
    @Column(name = "EMIRATE_Description", length = 300)
    private String emirateDescription;
    
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

    public String getEmirateCode() {
        return emirateCode;
    }

    public String getEmirateName() {
        return emirateName;
    }

    public String getEmirateDescription() {
        return emirateDescription;
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

    public void setEmirateCode(String emirateCode) {
        this.emirateCode = emirateCode;
    }

    public void setEmirateName(String emirateName) {
        this.emirateName = emirateName;
    }

    public void setEmirateDescription(String emirateDescription) {
        this.emirateDescription = emirateDescription;
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
        if (!(object instanceof MSTEmirates)) {
            return false;
        }
        MSTEmirates other = (MSTEmirates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.MSTEmirates[ id=" + id + " ]";
    }
    
}
