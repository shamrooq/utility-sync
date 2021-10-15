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
public class BomDAOImp implements BomDAO {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Override
    public List<Boms> findAll() {
        return entityManager.createNamedQuery("Boms.findAll", Boms.class).getResultList();
    }
    
    @Override
    public List<BOMDTO> findAllByORD(String ordNumber){
        TypedQuery<BOMDTO> query;
        query = entityManager.createNamedQuery("Requests.findByUtilityNumber", BOMDTO.class);
        query.setParameter("utilityNumber", ordNumber);
              return  query.getResultList();
    }
    
    @Override
    public BOMDTO createNewUSBOM(String utilityNumber, String bomStatus)throws DataAccessException    
    {
        
        entityManager.createNativeQuery("exec [dbo].[SP_InsertNewBOM] '" + utilityNumber + "','"+bomStatus+"'").executeUpdate();
        
        String q = "select * from [dbo].[BOM] where UTILITY_NUMBER = '"+utilityNumber+"'";
        Boms rec = (Boms) entityManager.createNativeQuery(q, Boms.class).getResultList().get(0);
        
        return new ModelMapper().map(rec, BOMDTO.class);
    }
    
    @Override
    public void updateSRDetail(BOMDTO dto)throws DataAccessException {
        Boms entity = getEntity(dto.getId());
        if(entity == null) {
            throw new DataAccessException("Incident not found");
        }
        new ModelMapper().map(dto, entity);
        
        entityManager.merge(entity);
    }
    
    private Boms getEntity(Long id) {
        Boms entity = null;
        
        try {
            entity = entityManager.find(Boms.class, id);
        } catch(NoResultException ex) {
            return null;
        }
        
        return entity;
    }
    
    /* ***
    *
    *
    */
    
    private Boms getEntity(String utilityNumber) {
        Boms entity = null;
        
        try {
            entity = entityManager.find(Boms.class, utilityNumber);
        } catch(NoResultException ex) {
            return null;
        }
        
        return entity;
    }
    
    @Override
    public void updateRequestStatus(String utilityNumber, Integer status){
        TypedQuery<Boms> query = entityManager.createNamedQuery("Boms.updateRequestStatus", Boms.class);
        query.setParameter("requestStatus", status);
        query.setParameter("utilityNumber", utilityNumber);

        int update = query.executeUpdate();

        if (update == 0) {
            throw new DataAccessException("Update Status failed");
        }
    }
    
}
