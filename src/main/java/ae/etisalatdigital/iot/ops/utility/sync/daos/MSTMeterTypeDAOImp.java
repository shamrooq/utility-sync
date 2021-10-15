/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;

import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTMeterTypes;
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
public class MSTMeterTypeDAOImp implements MSTMeterTypeDAO {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Override
    public List<MSTMeterTypes> findAll() {
        return entityManager.createNamedQuery("MSTMeterTypes.findAll", MSTMeterTypes.class).getResultList();
    }
    
    
    
    
    
    /* ***
    *
    *
    */
   
    
}
