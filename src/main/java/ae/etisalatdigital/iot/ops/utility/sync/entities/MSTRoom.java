/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author appadmin
 */

@Entity
@Table(name = "MST_Room")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTRoom.findAll", query = "SELECT m FROM MSTRoom m")
})
public class MSTRoom implements Serializable { 
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROOM_ID", nullable = false)
    private Long id;
    
    @Column(name = "ROOM_Code", length = 50)
    private String roomCode;
    
    @Column(name = "ROOM_Description", length = 200)
    private String roomDescription;
    
    @OneToMany(mappedBy = "mstRoom")
    private List<BOMGatewaysEst> bomGatewayEstListRoomId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public Long getId() {
        return id;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public String getRoomDescription() {
        return roomDescription;
    }
    
    
    
    /**
     * 
     * @param id 
     */
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSTRoom)) {
            return false;
        }
        MSTRoom other = (MSTRoom) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString() {
        return "MSTRoom{" + "id=" + id + ", roomCode=" + roomCode + ", roomDescription=" + roomDescription + '}';
    }

    
    
    
    
    
    
}
