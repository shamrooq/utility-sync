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
@Table(name = "MST_Floor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTFloor.findAll", query = "SELECT m FROM MSTFloor m")
})
public class MSTFloor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FLOOR_ID", nullable = false)
    private Long id;
    
    @Column(name = "FLOOR_Code", length = 50)
    private String floorCode;
    
    @Column(name = "FLOOR_Description", length = 200)
    private String floorDescription;

    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFloorCode() {
        return floorCode;
    }

    public String getFloorDescription() {
        return floorDescription;
    }
    
    
    /** 
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }

    public void setFloorDescription(String floorDescription) {
        this.floorDescription = floorDescription;
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
        if (!(object instanceof MSTFloor)) {
            return false;
        }
        MSTFloor other = (MSTFloor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.MSTFloor[ id=" + id + " ]";
    }
    
}
