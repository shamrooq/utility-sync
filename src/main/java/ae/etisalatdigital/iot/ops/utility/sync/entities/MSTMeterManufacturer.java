/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author appadmin
 */
@Entity
@Table(name = "MST_Meter_Manufacturer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTMeterManufacturer.findAll", query = "SELECT m FROM MSTMeterManufacturer m")
   ,@NamedQuery(name = "MSTMeterManufacturer.findAllByUtilityId", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.MSTMeterManufacturerDTO(m.id,m.utilityId,m.manufacturerCode,m.manufacturerName,m.manufacturerDescription) FROM MSTMeterManufacturer m where m.utilityId = :utilityId")
})
public class MSTMeterManufacturer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MANUFACTURER_ID", nullable = false)
    private Long id;
    
    @Column(name = "UTILITY_Id")
    private Long utilityId;
    
    @Column(name = "MANUFACTURER_Code", length = 100)
    private String manufacturerCode;
    
    @Column(name = "MANUFACTURER_Name", length = 100)
    private String manufacturerName;
    
    @Column(name = "MANUFACTURER_Description", length = 200)
    private String manufacturerDescription;
    
    @OneToMany(mappedBy = "meterManufacturerModel")
    private Collection<BOMMeters> metersCollection;
    
    @JoinColumn(name = "MANUFACTURER_ID", referencedColumnName = "MODEL_ID", insertable = false, updatable = false)
    @ManyToOne
    private MSTMeterModel meterModelModel;
    
    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getManufacturerCode() {
        return manufacturerCode;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getManufacturerDescription() {
        return manufacturerDescription;
    }

    public Collection<BOMMeters> getMetersCollection() {
        return metersCollection;
    }

    public MSTMeterModel getMeterModelModel() {
        return meterModelModel;
    }

    public Long getUtilityId() {
        return utilityId;
    }
    
    
    
    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setManufacturerDescription(String manufacturerDescription) {
        this.manufacturerDescription = manufacturerDescription;
    }

    public void setMetersCollection(Collection<BOMMeters> metersCollection) {
        this.metersCollection = metersCollection;
    }

    public void setMeterModelModel(MSTMeterModel meterModelModel) {
        this.meterModelModel = meterModelModel;
    }

    public void setUtilityId(Long utilityId) {
        this.utilityId = utilityId;
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
        if (!(object instanceof MSTMeterManufacturer)) {
            return false;
        }
        MSTMeterManufacturer other = (MSTMeterManufacturer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.MSTMeterManufacturer[ id=" + id + " ]";
    }
    
}
