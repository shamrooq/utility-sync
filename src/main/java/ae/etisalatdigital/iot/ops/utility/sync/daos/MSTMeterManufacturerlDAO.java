/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTMeterManufacturer;
import java.util.List;

/**
 *
 * @author appadmin
 */
public interface MSTMeterManufacturerlDAO {
    abstract List<MSTMeterManufacturer> findAll();
    abstract List<MSTMeterManufacturer> findAllByUtilityId(Long utilityId);
}
