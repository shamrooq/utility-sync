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
public class MSTFloorDTO {
    
    private Long id;
    private String floorCode;
    private String floorDescription;

    public MSTFloorDTO() {
    }

    public MSTFloorDTO(Long id, String floorCode, String floorDescription) {
        this.id = id;
        this.floorCode = floorCode;
        this.floorDescription = floorDescription;
    }

    public Long getId() {
        return id;
    }

    public String getFloorCode() {
        return floorCode;
    }

    public String getFloorDescription() {
        return floorDescription;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }

    public void setFloorDescription(String floorDescription) {
        this.floorDescription = floorDescription;
    }
    
    
}
