/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.buses;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.daos.BomDAO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Boms;
import java.math.BigDecimal;
import java.util.Date;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author au_mobility
 */
@Stateless
public class BomBus {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Inject
    private BomDAO bomDao;
    
    public List<Boms> findAll(){
        return bomDao.findAll();
    }
    
    
    public BOMDTO createNewUSBOM(String utilityNumber, String bomStatus)throws DataAccessException{
        
        return bomDao.createNewUSBOM(utilityNumber, bomStatus);
    }
    
    
    public List<BOMDTO> findAllByORD(String ordNumber){
        return bomDao.findAllByORD(ordNumber);
    }
    
    public BOMDTO findAllByUN(String utilityNumber){
        return bomDao.findAllByORD(utilityNumber).get(0);
    }
    
    public void updateSRDetails(BOMDTO dto){
        bomDao.updateSRDetail(dto);
    }
    
    public void updateRequestStatus(String utilityNumber, Integer status)
    {
        bomDao.updateRequestStatus(utilityNumber, status);
    }
   
    
  
}
