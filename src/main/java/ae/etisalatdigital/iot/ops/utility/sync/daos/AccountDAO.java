/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.AccountDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Accounts;
import java.util.List;

/**
 *
 * @author au_mobility
 */
public interface AccountDAO {
    abstract List<Accounts> findAll();
    abstract List<AccountDTO> findAllByORD(String ordNumber);
    
    abstract void updateSRDetail(AccountDTO dto)throws DataAccessException;
   
    abstract void updateRequestStatus(String utilityNumber, Integer status);
}
