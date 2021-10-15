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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author appadmin
 */
@Entity
@Table(name = "US_Account", catalog = "UTILITYSYNC", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounts.findAll", query = "SELECT m FROM Accounts m")
        ,@NamedQuery(name = "Accounts.findAllByAccountNumber", query = "SELECT m FROM Accounts m where m.accountNumber = :accountNumber")
})
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ACCOUNT_ID", nullable = false)
    private Long id;
    
    @Column(name = "ACCOUNT_Number", nullable = false)
    private Long accountNumber;
    
    @Size(max = 20)
    @Column(name = "Contact_Number_Primary", length = 20)
    private String contactNumberPrimary;
    
    @Size(max = 20)
    @Column(name = "Contact_Number_Additional", length = 20)
    private String contactNumberAdditional;
    
    @Column(name = "Created_Date")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date createdDate;
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    
    @Size(max = 50)
    @Column(name = "Emirate", length = 50)
    private String emirateName;
    
    @Size(max = 50)
    @Column(name = "Emirate_Region", length = 50)
    private String emirateRegion;
    
    @Size(max = 200)
    @Column(name = "Company_Customer", length = 200)
    private String companyCustomer;
    
    @Size(max = 200)
    @Column(name = "Company_Vendor", length = 200)
    private String companyVendor;
    
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public Long getId() {
        return id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getContactNumberPrimary() {
        return contactNumberPrimary;
    }

    public String getContactNumberAdditional() {
        return contactNumberAdditional;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public String getEmirateName() {
        return emirateName;
    }

    public String getEmirateRegion() {
        return emirateRegion;
    }

    public String getCompanyCustomer() {
        return companyCustomer;
    }

    public String getCompanyVendor() {
        return companyVendor;
    }
    
    
    /**
     * 
     * @param id 
     */
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setContactNumberPrimary(String contactNumberPrimary) {
        this.contactNumberPrimary = contactNumberPrimary;
    }

    public void setContactNumberAdditional(String contactNumberAdditional) {
        this.contactNumberAdditional = contactNumberAdditional;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setEmirateName(String emirateName) {
        this.emirateName = emirateName;
    }

    public void setEmirateRegion(String emirateRegion) {
        this.emirateRegion = emirateRegion;
    }

    public void setCompanyCustomer(String companyCustomer) {
        this.companyCustomer = companyCustomer;
    }

    public void setCompanyVendor(String companyVendor) {
        this.companyVendor = companyVendor;
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
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.etisalatdigital.iot.ops.utility.sync.entities.Accounts[ id=" + id + " ]";
    }
    
}
