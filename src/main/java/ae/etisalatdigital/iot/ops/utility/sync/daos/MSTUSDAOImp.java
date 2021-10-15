/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;


import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTUS;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.MSTUSDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;


/**
 *
 * @author au_mobility
 */
@Stateless
public class MSTUSDAOImp implements MSTUSDAO {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Override
    public List<MSTUS> findAll() {
        return entityManager.createNamedQuery("MSTUS.findAll", MSTUS.class).getResultList();
    }
    
    
    /**
     * 
     * @return 
     */
    
    @Override
    public MSTUSDTO findOne() {
        MSTUS entity = entityManager.createNamedQuery("MSTUS.findAll", MSTUS.class).getResultList().get(0);
        if (entity == null)
            return null;
        
        return new ModelMapper().map(entity, MSTUSDTO.class);
    }
    
    /**
     * 
     * @param dto
     * @throws DataAccessException 
     */
    
    @Override
    public void updateBaseline(MSTUSDTO dto) throws DataAccessException {
        MSTUS entity = getEntity(dto.getId());
        if(entity == null) {
            throw new DataAccessException("Incident not found");
        }
        new ModelMapper().map(dto, entity);
        
        entityManager.merge(entity);
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    
    private MSTUS getEntity(Long id) {
        MSTUS entity = null;
        
        try {
            entity = entityManager.find(MSTUS.class, id);
        } catch(NoResultException ex) {
            return null;
        }
        
        return entity;
    }
   
    
}
