/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.buses;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.daos.RequestDAO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.RequestDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Requests;
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
public class RequestBus {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Inject
    private RequestDAO requestDao;
    
    public List<Requests> findAll(){
        return requestDao.findAll();
    }
    
    
    public RequestDTO createNewUSRequest(String utilityNumber, Long accountNumber, String customerCompany, String emirateName, String emirateRegionName, String premiseSubStationNumber, String vendorName,
                                           String contactPrimaryNumber, String contactSecondaryNumber,
                                           BigDecimal premiseLatitude, BigDecimal premiseLongitude,
                                           String premiseBuildingName, String premisePlotNumber, String premiseId, String premiseType, String premiseAddress,
                                           Long usNumber,Date surveyDateSchedule,String createdBySignature,String createdByName)throws DataAccessException{
        
        return requestDao.createNewUSRequest(utilityNumber, accountNumber, customerCompany, emirateName, emirateRegionName, premiseSubStationNumber, vendorName, contactPrimaryNumber, contactSecondaryNumber, premiseLatitude, premiseLongitude, premiseBuildingName, premisePlotNumber, premiseId, premiseType, premiseAddress, usNumber, surveyDateSchedule, createdBySignature, createdByName);
    }
    
    
    public List<RequestDTO> findAllByORD(String ordNumber){
        return requestDao.findAllByORD(ordNumber);
    }
    
    public RequestDTO findAllByUN(String utilityNumber){
        return requestDao.findAllByORD(utilityNumber).get(0);
    }
    
    public void updateSRDetails(RequestDTO dto){
        requestDao.updateSRDetail(dto);
    }
    
    public void updateRequestStatus(String utilityNumber, Integer status)
    {
        requestDao.updateRequestStatus(utilityNumber, status);
    }
   
    
  
}
