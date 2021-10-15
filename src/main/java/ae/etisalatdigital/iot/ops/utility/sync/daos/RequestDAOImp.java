/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.RequestDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Requests;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.modelmapper.ModelMapper;

/**
 *
 * @author au_mobility
 */
@Stateless
public class RequestDAOImp implements RequestDAO {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Override
    public List<Requests> findAll() {
        return entityManager.createNamedQuery("Requests.findAll", Requests.class).getResultList();
    }
    
    @Override
    public List<RequestDTO> findAllByORD(String ordNumber){
        TypedQuery<RequestDTO> query;
        query = entityManager.createNamedQuery("Requests.findByUtilityNumber", RequestDTO.class);
        query.setParameter("utilityNumber", ordNumber);
              return  query.getResultList();
    }
    
    @Override
    public RequestDTO createNewUSRequest(String utilityNumber, Long accountNumber, String customerCompany, String emirateName, String emirateRegionName, String premiseSubStationNumber, String vendorName,
                                           String contactPrimaryNumber, String contactSecondaryNumber,
                                           BigDecimal premiseLatitude, BigDecimal premiseLongitude,
                                           String premiseBuildingName, String premisePlotNumber, String premiseId, String premiseType, String premiseAddress,
                                           Long usNumber,Date surveyDateSchedule,String createdBySignature,String createdByName)throws DataAccessException    
    {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date surveyDate = new Date(System.currentTimeMillis());
        entityManager.createNativeQuery("exec [dbo].[SP_InsertNewRequest] '" + utilityNumber + "',"+accountNumber
                                        +",N'" + customerCompany +"','"+ emirateName +"',N'"+emirateRegionName
                                        +"','"+premiseSubStationNumber+"','"+vendorName+"','"+contactPrimaryNumber+"','"+contactSecondaryNumber+"',"+premiseLatitude+","+premiseLongitude
                                        +",N'"+premiseBuildingName+"','"+ premiseId+"','"+premisePlotNumber+"','"+premiseType+"',N'"+premiseAddress+"',"+usNumber+",'"+formatter.format(surveyDate)+"',N'"+createdBySignature+"','"+createdByName+"'").executeUpdate();
        
        String q = "select * from [dbo].[US_Request] where UTILITY_NUMBER = '"+utilityNumber+"'";
        Requests rec = (Requests) entityManager.createNativeQuery(q, Requests.class).getResultList().get(0);
        
        return new ModelMapper().map(rec, RequestDTO.class);
    }
    
    @Override
    public void updateSRDetail(RequestDTO dto)throws DataAccessException {
        Requests entity = getEntity(dto.getId());
        if(entity == null) {
            throw new DataAccessException("Incident not found");
        }
        new ModelMapper().map(dto, entity);
        
        entityManager.merge(entity);
    }
    
    private Requests getEntity(Long id) {
        Requests entity = null;
        
        try {
            entity = entityManager.find(Requests.class, id);
        } catch(NoResultException ex) {
            return null;
        }
        
        return entity;
    }
    
    /* ***
    *
    *
    */
    
    private Requests getEntity(String utilityNumber) {
        Requests entity = null;
        
        try {
            entity = entityManager.find(Requests.class, utilityNumber);
        } catch(NoResultException ex) {
            return null;
        }
        
        return entity;
    }
    
    @Override
    public void updateRequestStatus(String utilityNumber, Integer status){
        TypedQuery<Requests> query = entityManager.createNamedQuery("Requests.updateRequestStatus", Requests.class);
        query.setParameter("requestStatus", status);
        query.setParameter("utilityNumber", utilityNumber);

        int update = query.executeUpdate();

        if (update == 0) {
            throw new DataAccessException("Update Status failed");
        }
    }
    
}
