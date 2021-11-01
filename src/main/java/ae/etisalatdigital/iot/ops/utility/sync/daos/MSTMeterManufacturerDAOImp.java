/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.MSTProtocolDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTMeterManufacturer;
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
public class MSTMeterManufacturerDAOImp implements MSTMeterManufacturerlDAO {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Override
    public List<MSTMeterManufacturer> findAll() {
        return entityManager.createNamedQuery("MSTMeterManufacturer.findAll", MSTMeterManufacturer.class).getResultList();
    }
    
    /**
     * 
     * @param utilityId
     * @return 
     */
    @Override
    public List<MSTMeterManufacturer> findAllByUtilityId(Long utilityId) {
        TypedQuery<MSTMeterManufacturer> query = entityManager.createNamedQuery("MSTMeterManufacturer.findAllByUtilityId", MSTMeterManufacturer.class);
        query.setParameter("utilityId", utilityId);
        return  query.getResultList();
    }
}
