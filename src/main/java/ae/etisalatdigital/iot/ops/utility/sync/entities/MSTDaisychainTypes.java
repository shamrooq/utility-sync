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
@Table(name = "MST_Daisychain_Type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTDaisychainTypes.findAll", query = "SELECT m FROM MSTDaisychainTypes m")
})
public class MSTDaisychainTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DAISYCHAIN_ID", nullable = false)
    private Long id;
    
    @Column(name = "DAISYCHAIN_Type", length = 10)
    private String daisychainCode;
    
    @Column(name = "DAISYCHAIN_Name", length = 200)
    private String daisychainName;
    
    

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public Long getId() {
        return id;
    }

    public String getDaisychainCode() {
        return daisychainCode;
    }

    public String getDaisychainName() {
        return daisychainName;
    }

    

    
    
    /**
     * 
     * @param id 
     */

    public void setId(Long id) {
        this.id = id;
    }

    public void setDaisychainCode(String daisychainCode) {
        this.daisychainCode = daisychainCode;
    }

    public void setDaisychainName(String daisychainName) {
        this.daisychainName = daisychainName;
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
        if (!(object instanceof MSTDaisychainTypes)) {
            return false;
        }
        MSTDaisychainTypes other = (MSTDaisychainTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.MSTDaisychainTypes[ id=" + id + " ]";
    }
    
}
