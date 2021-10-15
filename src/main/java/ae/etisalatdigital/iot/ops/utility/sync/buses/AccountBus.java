/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.buses;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.daos.AccountDAO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.AccountDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Accounts;
import java.math.BigDecimal;
import java.util.Date;

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
public class AccountBus {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Inject
    private AccountDAO accountDao;
    
    public List<Accounts> findAll(){
        return accountDao.findAll();
    }
    
    public List<AccountDTO> findAllByORD(String ordNumber){
        return accountDao.findAllByORD(ordNumber);
    }
    
    public AccountDTO findAllByUN(String utilityNumber){
        return accountDao.findAllByORD(utilityNumber).get(0);
    }
    
    public void updateSRDetails(AccountDTO dto){
        accountDao.updateSRDetail(dto);
    }
    
    public void updateRequestStatus(String utilityNumber, Integer status)
    {
        accountDao.updateRequestStatus(utilityNumber, status);
    }
   
    
  
}
