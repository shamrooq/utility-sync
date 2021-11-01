/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "MST_Meter_Model")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTMeterModel.findAll", query = "SELECT m FROM MSTMeterModel m")
   ,@NamedQuery(name = "MSTMeterModel.findAllByManufacturerId", query = "SELECT new ae.etisalatdigital.iot.ops.utility.sync.dtos.MSTMeterModelDTO(m.id,m.manufacturerId,m.modelCode,m.modelTitle,m.modelDescription) FROM MSTMeterModel m where m.manufacturerId = :manufacturerId")
})
public class MSTMeterModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MODEL_ID", nullable = false)
    private Long id;
    
    @Column(name = "MANUFACTURER_ID")
    private Long manufacturerId;
    
    @Column(name = "MODEL_Code", length = 100)
    private String modelCode;
    
    @Column(name = "MODEL_Title", length = 100)
    private String modelTitle;
    
    @Column(name = "MODEL_Description", length = 200)
    private String modelDescription;
    
    @OneToMany(mappedBy = "meterModelModel")
    private Collection<MSTMeterManufacturer> manufacturerCollection;
    
    @OneToMany(mappedBy = "mstMeterModel")
    private List<BOMMeters> modelsCollection;
    
    public Long getId() {
        return id;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }
    
    

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getModelCode() {
        return modelCode;
    }

    public String getModelTitle() {
        return modelTitle;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public Collection<MSTMeterManufacturer> getManufacturerCollection() {
        return manufacturerCollection;
    }
    
    
    

    public void setId(Long id) {
        this.id = id;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
    
    

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public void setModelTitle(String modelTitle) {
        this.modelTitle = modelTitle;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public void setManufacturerCollection(Collection<MSTMeterManufacturer> manufacturerCollection) {
        this.manufacturerCollection = manufacturerCollection;
    }

    public List<BOMMeters> getModelsCollection() {
        return modelsCollection;
    }

    public void setModelsCollection(List<BOMMeters> modelsCollection) {
        this.modelsCollection = modelsCollection;
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
        if (!(object instanceof MSTMeterModel)) {
            return false;
        }
        MSTMeterModel other = (MSTMeterModel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.MSTMeterModel[ id=" + id + " ]";
    }
    
}
