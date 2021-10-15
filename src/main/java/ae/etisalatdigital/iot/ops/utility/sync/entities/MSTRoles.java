/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.entities;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/**
 *
 * @author au_mobility
 */
@Entity
@Table(name = "MST_Role", catalog = "UTILITYSYNC", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MSTRoles.findAll", query = "SELECT m FROM MSTRoles m")
    , @NamedQuery(name = "MSTRoles.findByRoleName", query = "SELECT m FROM MSTRoles m WHERE m.roleName = :roleName")
    , @NamedQuery(name = "MSTRoles.findByRoleDescription", query = "SELECT m FROM MSTRoles m WHERE m.roleDescription = :roleDescription")})
public class MSTRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ROLE_TITLE", nullable = false, length = 50)
    private String roleName;
    @Size(max = 100)
    @Column(name = "ROLE_Description", length = 100)
    private String roleDescription;
    
    @Column(name = "Is_Deleted")
    private Boolean isDeleted;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userRole")
    private Collection<MSTUsers> mSTUsersCollection;

    public MSTRoles() {
    }

    public MSTRoles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    
    
    

    @XmlTransient
    public Collection<MSTUsers> getMSTUsersCollection() {
        return mSTUsersCollection;
    }

    public void setMSTUsersCollection(Collection<MSTUsers> mSTUsersCollection) {
        this.mSTUsersCollection = mSTUsersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleName != null ? roleName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSTRoles)) {
            return false;
        }
        MSTRoles other = (MSTRoles) object;
        if ((this.roleName == null && other.roleName != null) || (this.roleName != null && !this.roleName.equals(other.roleName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.etisalat.entitiesms.MSTRoles[ roleName=" + roleName + " ]";
    }
    
}

