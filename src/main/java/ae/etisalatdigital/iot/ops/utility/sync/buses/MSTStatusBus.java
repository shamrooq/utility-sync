/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.buses;

import ae.etisalatdigital.iot.ops.utility.sync.daos.MSTStatusDAO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.MSTEmirateDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTStatus;

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
public class MSTStatusBus {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Inject
    private MSTStatusDAO statusDao;
    
    public List<MSTStatus> findAll(){
        return statusDao.findAll();
    }
    
   
   
    
  
}
