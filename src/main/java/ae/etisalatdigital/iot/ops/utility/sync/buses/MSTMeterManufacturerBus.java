/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.buses;

import ae.etisalatdigital.iot.ops.utility.sync.daos.MSTMeterManufacturerlDAO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTMeterManufacturer;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;



/**
 *
 * @author au_mobility
 */
@Stateless
public class MSTMeterManufacturerBus {
    
    @Inject
    private MSTMeterManufacturerlDAO meterManufacturerDao;
    
    public List<MSTMeterManufacturer> findAll(){
        return meterManufacturerDao.findAll();
    }
    
   
   
    
  
}
