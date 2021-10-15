/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;


import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.BOMGatewaysEst;
import java.util.List;

/**
 *
 * @author au_mobility
 */
public interface BOMGatewayEstDAO {
    abstract List<BOMGatewayEstDTO> findAll();
    abstract List<BOMGatewayEstDTO> findAllByBomId(Long bomId);
    
    abstract List<BOMGatewayEstDTO> findAllByBomIdAndBomMeterType(Long bomId, String bomMeterType);
    
    abstract Boolean addNewGatewayEstByBomId(Long bomId, String gatewayBomType, String proposedGateType, int noOfGateways, int metersPerGateway,String csbleEtimation,String gatewayLocation);
        
    
    abstract void updateGatewayDetail(BOMGatewayEstDTO dto)throws DataAccessException;
    
    abstract void updateRequestStatus(String mmsReference, Integer status);
    
    abstract Boolean delete(Long id);
}
