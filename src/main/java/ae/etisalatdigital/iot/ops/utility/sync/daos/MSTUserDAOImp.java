/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;


import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTUsers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author au_mobility
 */
@Stateless
public class MSTUserDAOImp implements MSTUserDAO {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Override
    public List<MSTUsers> findAll() {
        return entityManager.createNamedQuery("MSTUsers.findAll", MSTUsers.class).getResultList();
    }
    
    
    
    
    
    /* ***
    *
    *
    */
   
    
}
