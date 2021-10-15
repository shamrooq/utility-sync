/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author au_mobility
 */
public class MSTDaisychainTypeDTO {
    
    private Long id;
    
    
    private String daisychainCode;
    
    
    private String daisychainName;
    
    
    
    public MSTDaisychainTypeDTO(){}

    public MSTDaisychainTypeDTO(Long id, String daisychainCode, String daisychainName) {
        this.id = id;
        this.daisychainCode = daisychainCode;
        this.daisychainName = daisychainName;
    }

    

    public Long getId() {
        return id;
    }

    public String getDaisychainCode() {
        return daisychainCode;
    }

    public String getDaisychainName() {
        return daisychainName;
    }

    
    

    public void setId(Long id) {
        this.id = id;
    }

    public void setDaisychainCode(String daisychainCode) {
        this.daisychainCode = daisychainCode;
    }

    public void setDaisychainName(String daisychainName) {
        this.daisychainName = daisychainName;
    }

    
    
    
}
