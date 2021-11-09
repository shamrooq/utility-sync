/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.BusinessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.SimDetailsDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.SimDetails;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author au_mobility
 */
public interface SimDetailsDAO {
    abstract List<SimDetails> findAll();
    abstract List<SimDetailsDTO> findBySimICCID(BigInteger simICCID);
    abstract void addNewSimDetails(SimDetailsDTO simDetailsDTO) throws BusinessException;
    abstract void updateSimDetails(SimDetailsDTO simDetailsDTO);
}
