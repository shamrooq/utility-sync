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
@Table(name = "MST_Meter_Type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTMeterTypes.findAll", query = "SELECT m FROM MSTMeterTypes m")
})
public class MSTMeterTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "METER_TYPE_ID", nullable = false)
    private Long id;
    
    @Column(name = "METER_TYPE_Code", length = 10)
    private String meterTypeCode;
    
    @Column(name = "METER_TYPE_Name", length = 200)
    private String metereTypeName;
    
    @Column(name = "METER_TYPE_Description", length = 300)
    private String meterTypeDescription;

    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public Long getId() {
        return id;
    }

    public String getMeterTypeCode() {
        return meterTypeCode;
    }

    public String getMetereTypeName() {
        return metereTypeName;
    }

    public String getMeterTypeDescription() {
        return meterTypeDescription;
    }
    
    
    /**
     * 
     * @param id 
     */
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setMeterTypeCode(String meterTypeCode) {
        this.meterTypeCode = meterTypeCode;
    }

    public void setMetereTypeName(String metereTypeName) {
        this.metereTypeName = metereTypeName;
    }

    public void setMeterTypeDescription(String meterTypeDescription) {
        this.meterTypeDescription = meterTypeDescription;
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
        if (!(object instanceof MSTMeterTypes)) {
            return false;
        }
        MSTMeterTypes other = (MSTMeterTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.MSTMeterTypes[ id=" + id + " ]";
    }
    
}
