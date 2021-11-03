/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models;

/**
 *
 * @author appadmin
 */
public class EquipmentResponseModel {
    private Long code;
    private String description;
    private Long errorNumber;
    private String errorCode;
    private String stackTrace;

    public Long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(Long errorNumber) {
        this.errorNumber = errorNumber;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
    
    
}
