/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.iot.ops.utility.sync.dtos.MSTVendorDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTVendors;
import java.util.List;

/**
 *
 * @author au_mobility
 */
public interface MSTVendorDAO {
    abstract List<MSTVendors> findAll();
}
