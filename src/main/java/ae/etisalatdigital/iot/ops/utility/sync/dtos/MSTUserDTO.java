/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTRoles;
import java.util.Date;


/**
 *
 * @author appadmin
 */
public class MSTUserDTO {
    
    private String userID;
    
    private String userName;
    
    
    private String userCode;
    
    
    private Date createdDate;
    
    private Date modifiedDate;
    
    
    private Boolean isDisable;
    
    
    private Boolean isFirstLogin;
    
    
    private MSTRoles userRole;

    public MSTUserDTO() {
    }

    public MSTUserDTO(String userID, String userName, String userCode, Date createdDate, Date modifiedDate, Boolean isDisable, Boolean isFirstLogin, MSTRoles userRole) {
        this.userID = userID;
        this.userName = userName;
        this.userCode = userCode;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isDisable = isDisable;
        this.isFirstLogin = isFirstLogin;
        this.userRole = userRole;
    }

    public String getUserID() {
        return userID;
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
    
    /**
     * 
     * @param userID 
     */
    
    public void setUserID(String userID) {
        this.userID = userID;
    }

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
    
    
    
    
}
