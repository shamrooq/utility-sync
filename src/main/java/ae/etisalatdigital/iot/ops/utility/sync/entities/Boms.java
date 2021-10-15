/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author au_addc
 */
@Entity
@Table(name = "BOM", catalog = "UTILITYSYNC", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boms.findAll", query = "SELECT m FROM Boms m")
  })
public class Boms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOM_ID", nullable = false)
    private Long id;
    
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "UTILITY_NUMBER", nullable = false, length = 50)
    private String utilityNumber;
    
    @Column(name = "BOM_Status", nullable = false, length = 50)
    private String bomStatus;
    
    @Column(name = "Remarks", nullable = false, length = 300)
    private String bomRemarks;
    
    @Column(name = "Surveyor_Name", nullable = false, length = 300)
    private String surveyorName;
    
    @Column(name = "Surveyor_Signature", nullable = false, length = 300)
    private String surveyorSignature;
    
    
    @Column(name = "Created_Date")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date createdDate;
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "Survey_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date surveyDate;

    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUtilityNumber() {
        return utilityNumber;
    }

    public String getBomStatus() {
        return bomStatus;
    }

    public String getBomRemarks() {
        return bomRemarks;
    }

    public String getSurveyorName() {
        return surveyorName;
    }

    public String getSurveyorSignature() {
        return surveyorSignature;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }
    
    /**
     * 
     * @param id 
     */
    
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setUtilityNumber(String utilityNumber) {
        this.utilityNumber = utilityNumber;
    }

    public void setBomStatus(String bomStatus) {
        this.bomStatus = bomStatus;
    }

    public void setBomRemarks(String bomRemarks) {
        this.bomRemarks = bomRemarks;
    }

    public void setSurveyorName(String surveyorName) {
        this.surveyorName = surveyorName;
    }

    public void setSurveyorSignature(String surveyorSignature) {
        this.surveyorSignature = surveyorSignature;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
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
        if (!(object instanceof Boms)) {
            return false;
        }
        Boms other = (Boms) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.Boms[ id=" + id + " ]";
    }
    
}
