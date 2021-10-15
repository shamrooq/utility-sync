/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.MSTProtocolDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTRoom;
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
public class MSTRoomDAOImp implements MSTRoomDAO {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Override
    public List<MSTRoom> findAll() {
        return entityManager.createNamedQuery("MSTRoom.findAll", MSTRoom.class).getResultList();
    }
    
    
}
