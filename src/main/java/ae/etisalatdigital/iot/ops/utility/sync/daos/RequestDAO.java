/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.RequestDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Requests;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author au_mobility
 */
public interface RequestDAO {
    abstract List<Requests> findAll();
    abstract List<RequestDTO> findAllByORD(String ordNumber);
    
    abstract void updateSRDetail(RequestDTO dto)throws DataAccessException;
    
    /**
     * 
     * @param utilityNumber
     * @param accountNumber
     * @param customerCompany
     * @param emirateName
     * @param emirateRegionName
     * @param premiseSubStationNumber
     * @param vendorName
     * @param contactPrimaryNumber
     * @param contactSecondaryNumber
     * @param premiseLatitude
     * @param premiseLongitude
     * @param premiseBuildingName
     * @param premisePlotNumber
     * @param premiseId
     * @param premiseType
     * @param premiseAddress
     * @param usNumber
     * @param surveyDateSchedule
     * @param createdBySignature
     * @param createdByName
     * @return 
     * @throws DataAccessException 
     */
    abstract RequestDTO createNewUSRequest(String utilityNumber, Long accountNumber, String customerCompany, String emirateName, String emirateRegionName, String premiseSubStationNumber, String vendorName,
                                           String contactPrimaryNumber, String contactSecondaryNumber,
                                           BigDecimal premiseLatitude, BigDecimal premiseLongitude,
                                           String premiseBuildingName, String premisePlotNumber, String premiseId, String premiseType, String premiseAddress,
                                           Long usNumber,Date surveyDateSchedule,String createdBySignature,String createdByName)throws DataAccessException;
    
    abstract void updateRequestStatus(String utilityNumber, Integer status);
}
