/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import java.util.Date;


/**
 *
 * @author appadmin
 */
public class AccountDTO {
    
    private Long id;
    
    
    private Long accountNumber;
    
    
    private String contactNumberPrimary;
    
    
    private String contactNumberAdditional;
    
     
    private Date createdDate;
    
    private Date modifiedDate;
    
    
    
    private String emirateName;
    
    
    private String emirateRegion;
    
    
    private String companyCustomer;
    
    
    private String companyVendor;

    public AccountDTO() {
    }

    public AccountDTO(Long id, Long accountNumber, String contactNumberPrimary, String contactNumberAdditional, Date createdDate, Date modifiedDate, String emirateName, String emirateRegion, String companyCustomer, String companyVendor) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.contactNumberPrimary = contactNumberPrimary;
        this.contactNumberAdditional = contactNumberAdditional;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.emirateName = emirateName;
        this.emirateRegion = emirateRegion;
        this.companyCustomer = companyCustomer;
        this.companyVendor = companyVendor;
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
    
    
    
    
}
