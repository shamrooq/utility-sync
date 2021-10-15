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
@Table(name = "MST_US")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTUS.findAll", query = "SELECT m FROM MSTUS m")
})
public class MSTUS implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "US_ID", nullable = false)
    private Long id;
    
    @Column(name = "US_Number")
    private Long usNumber;
    
    @Column(name = "US_Code", length = 5)
    private String usCode;
    
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public Long getUsNumber() {
        return usNumber;
    }

    public String getUsCode() {
        return usCode;
    }
    
    /**
     * 
     * @param id 
     */
    

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsNumber(Long usNumber) {
        this.usNumber = usNumber;
    }

    public void setUsCode(String usCode) {
        this.usCode = usCode;
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
        if (!(object instanceof MSTUS)) {
            return false;
        }
        MSTUS other = (MSTUS) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.MSTUS[ id=" + id + " ]";
    }
    
}
