/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;



/**
 *
 * @author au_mobility
 */
public class MSTUSDTO {
    
    private Long id;
    
    private Long usNumber;
    
    private String usCode;
    
    
    public MSTUSDTO(){}

    public MSTUSDTO(Long id, Long usNumber, String usCode) {
        this.id = id;
        this.usNumber = usNumber;
        this.usCode = usCode;
    }
    
    

    public Long getId() {
        return id;
    }

    public Long getUsNumber() {
        return usNumber;
    }

    public String getUsCode() {
        return usCode;
    }

    

    /**
     * 
     * @param id 
     */
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsNumber(Long usNumber) {
        this.usNumber = usNumber;
    }

    public void setUsCode(String usCode) {
        this.usCode = usCode;
    }

    
    
    
}
