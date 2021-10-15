/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Boms;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author au_mobility
 */
public interface BomDAO {
    abstract List<Boms> findAll();
    abstract List<BOMDTO> findAllByORD(String ordNumber);
    
    abstract void updateSRDetail(BOMDTO dto)throws DataAccessException;
    
    /**
     * 
     * @param utilityNumber
     * @param bomStatus
     * @return 
     * @throws DataAccessException
     * */
     
    abstract BOMDTO createNewUSBOM(String utilityNumber, String bomStatus)throws DataAccessException;
    
    abstract void updateRequestStatus(String utilityNumber, Integer status);
}
