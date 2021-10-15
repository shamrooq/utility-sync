/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;



/**
 *
 * @author au_addc
 */
public class MSTStatusDTO {
    
    
    private Long id;

    
    private String statusCode;
    
    
    private String statusStage;
    
    
    private String statusModule;
    
    
    private String statusDescription;
    
    public MSTStatusDTO(){}

    public MSTStatusDTO(Long id, String statusCode, String statusStage, String statusModule, String statusDescription) {
        this.id = id;
        this.statusCode = statusCode;
        this.statusStage = statusStage;
        this.statusModule = statusModule;
        this.statusDescription = statusDescription;
    }

    public Long getId() {
        return id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusStage() {
        return statusStage;
    }

    public String getStatusModule() {
        return statusModule;
    }

    public String getStatusDescription() {
        return statusDescription;
    }
    
    /**
     * 
     */

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusStage(String statusStage) {
        this.statusStage = statusStage;
    }

    public void setStatusModule(String statusModule) {
        this.statusModule = statusModule;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
    
    
    
    
    
}
