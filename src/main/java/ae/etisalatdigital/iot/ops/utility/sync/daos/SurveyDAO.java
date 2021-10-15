/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.SurveyDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Surveys;
import java.util.List;

/**
 *
 * @author au_mobility
 */
public interface SurveyDAO {
    abstract List<Surveys> findAll();
    abstract List<Surveys> findAllPOC();
    abstract List<SurveyDTO> findAllByORD(String ordNumber);
    
    abstract void updateSRDetail(SurveyDTO dto)throws DataAccessException;
    
    abstract void updateRequestStatus(String mmsReference, Integer status);
}
