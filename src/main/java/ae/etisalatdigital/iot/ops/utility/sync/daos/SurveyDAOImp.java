/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.SurveyDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Surveys;
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
public class SurveyDAOImp implements SurveyDAO {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Override
    public List<Surveys> findAll() {
        return entityManager.createNamedQuery("Surveys.findAll", Surveys.class).getResultList();
    }
    
    @Override
    public List<Surveys> findAllPOC() {
        return entityManager.createNamedQuery("Surveys.findAllPOC", Surveys.class).getResultList();
    }
    
    @Override
    public List<SurveyDTO> findAllByORD(String ordNumber){
        TypedQuery<SurveyDTO> query;
        query = entityManager.createNamedQuery("Surveys.findByUtilityNumber", SurveyDTO.class);
        query.setParameter("utilityNumber", ordNumber);
              return  query.getResultList();
    }
    
    @Override
    public void updateSRDetail(SurveyDTO dto)throws DataAccessException {
        Surveys entity = getEntity(dto.getId());
        if(entity == null) {
            throw new DataAccessException("Incident not found");
        }
        
        try {
            new ModelMapper().map(dto, entity);
            entityManager.merge(entity);
        } catch(NoResultException ex) {
            System.out.println("SurveyDAOImp.updateSRDetail():::"+ex.getMessage());
            
        }
    }
    
    private Surveys getEntity(Long id) {
        Surveys entity = null;
        
        try {
            entity = entityManager.find(Surveys.class, id);
        } catch(NoResultException ex) {
            System.out.println("SurveyDAOImp.getEntity():::"+ex.getMessage());
            return null;
        }
        
        return entity;
    }
    
    /* ***
    *
    *
    */
    
    private Surveys getEntityForUtilityNumber(String utilityNumber) {
        Surveys entity = null;
        
        try {
            entity = entityManager.find(Surveys.class, utilityNumber);
        } catch(NoResultException ex) {
            System.out.println("SurveyDAOImp.getEntity():::"+ex.getMessage());
            return null;
        }
        
        return entity;
    }
    
    @Override
    public void updateRequestStatus(String mmsReference, Integer status){
        TypedQuery<Surveys> query = entityManager.createNamedQuery("Surveys.updateRequestStatus", Surveys.class);
        query.setParameter("requestStatus", status);
        query.setParameter("mmsReference", mmsReference);

        int update = query.executeUpdate();

        if (update == 0) {
            throw new DataAccessException("Update Status failed");
        }
    }
    
}
