/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;



/**
 *
 * @author appadmin
 */
public class MSTGatewayTypeDTO {
 
    private Long id;
      
    private String gatewayTypeCode;
    
    private String gatewayTypeName;
    
    private String gatewayTypeDescription;

    public MSTGatewayTypeDTO() {
    }

    public MSTGatewayTypeDTO(Long id, String gatewayTypeCode, String gatewayTypeName, String gatewayTypeDescription) {
        this.id = id;
        this.gatewayTypeCode = gatewayTypeCode;
        this.gatewayTypeName = gatewayTypeName;
        this.gatewayTypeDescription = gatewayTypeDescription;
    }

    public Long getId() {
        return id;
    }

    public String getGatewayTypeCode() {
        return gatewayTypeCode;
    }

    public String getGatewayTypeName() {
        return gatewayTypeName;
    }

    public String getGatewayTypeDescription() {
        return gatewayTypeDescription;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGatewayTypeCode(String gatewayTypeCode) {
        this.gatewayTypeCode = gatewayTypeCode;
    }

    public void setGatewayTypeName(String gatewayTypeName) {
        this.gatewayTypeName = gatewayTypeName;
    }

    public void setGatewayTypeDescription(String gatewayTypeDescription) {
        this.gatewayTypeDescription = gatewayTypeDescription;
    }
    
    
}
