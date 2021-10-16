/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTFloor;
import java.util.List;

/**
 *
 * @author appadmin
 */
public interface MSTFloorDAO {
    abstract List<MSTFloor> findAll();
}
