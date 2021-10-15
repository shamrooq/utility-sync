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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author au_mobility
 */
@Entity
@Table(name = "MST_User", catalog = "UTILITYSYNC", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTUsers.findAll", query = "SELECT m FROM MSTUsers m")
})
public class MSTUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "User_ID", nullable = false, length = 50)
    private String userID;
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "USER_Name", nullable = false, length = 200)
    private String userName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USER_Code",nullable = false, length = 20)
    private String userCode;
    
    @Column(name = "Created_Date")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date createdDate;
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @Column(name = "Is_Disable")
    private Boolean isDisable;
    
    @Column(name = "Is_First_Login")
    private Boolean isFirstLogin;
    
    @JoinColumn(name = "User_Role", referencedColumnName = "ROLE_TITLE", nullable = false)
    @ManyToOne(optional = false)
    private MSTRoles userRole;
    
    public String getId() {
        return userID;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Boolean getIsDisable() {
        return isDisable;
    }

    public Boolean getIsFirstLogin() {
        return isFirstLogin;
    }

    public MSTRoles getUserRole() {
        return userRole;
    }
    
    /* Setter */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
    }

    public void setIsFirstLogin(Boolean isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public void setUserRole(MSTRoles userRole) {
        this.userRole = userRole;
    }
    
    
    public void setId(String userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSTUsers)) {
            return false;
        }
        MSTUsers other = (MSTUsers) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.MSTUsers[ id=" + userID + " ]";
    }
    
}
