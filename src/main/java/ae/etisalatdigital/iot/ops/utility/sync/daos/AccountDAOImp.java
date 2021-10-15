/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.AccountDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Accounts;
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
public class AccountDAOImp implements AccountDAO {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Override
    public List<Accounts> findAll() {
        return entityManager.createNamedQuery("Requests.findAll", Accounts.class).getResultList();
    }
    
    @Override
    public List<AccountDTO> findAllByORD(String ordNumber){
        TypedQuery<AccountDTO> query;
        query = entityManager.createNamedQuery("Requests.findByUtilityNumber", AccountDTO.class);
        query.setParameter("utilityNumber", ordNumber);
              return  query.getResultList();
    }
    
    
    
    @Override
    public void updateSRDetail(AccountDTO dto)throws DataAccessException {
        Accounts entity = getEntity(dto.getId());
        if(entity == null) {
            throw new DataAccessException("Incident not found");
        }
        new ModelMapper().map(dto, entity);
        
        entityManager.merge(entity);
    }
    
    private Accounts getEntity(Long id) {
        Accounts entity = null;
        
        try {
            entity = entityManager.find(Accounts.class, id);
        } catch(NoResultException ex) {
            return null;
        }
        
        return entity;
    }
    
    /* ***
    *
    *
    */
    
    private Accounts getEntity(String utilityNumber) {
        Accounts entity = null;
        
        try {
            entity = entityManager.find(Accounts.class, utilityNumber);
        } catch(NoResultException ex) {
            return null;
        }
        
        return entity;
    }
    
    @Override
    public void updateRequestStatus(String utilityNumber, Integer status){
        TypedQuery<Accounts> query = entityManager.createNamedQuery("Requests.updateRequestStatus", Accounts.class);
        query.setParameter("requestStatus", status);
        query.setParameter("utilityNumber", utilityNumber);

        int update = query.executeUpdate();

        if (update == 0) {
            throw new DataAccessException("Update Status failed");
        }
    }
    
}
