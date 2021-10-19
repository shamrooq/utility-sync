/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.buses;

import ae.etisalatdigital.iot.ops.utility.sync.daos.MSTMeterModelDAO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTMeterModel;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;



/**
 *
 * @author au_mobility
 */
@Stateless
public class MSTMeterModelBus {
    
    @Inject
    private MSTMeterModelDAO meterModelDao;
    
    public List<MSTMeterModel> findAll(){
        return meterModelDao.findAll();
    }
    
   
   
    
  
}
