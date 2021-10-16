/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;


import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.beans.installation.UtilityGatewayMeterSemantics;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.BOMMeters;
import java.util.List;

/**
 *
 * @author au_mobility
 */
public interface BOMMeterDAO {
    abstract List<BOMMeters> findAll();
    abstract List<BOMMeterDTO> findAllByBomId(Long bomId);
    
    abstract List<BOMMeterDTO> findAllByBomIdAndBomMeterType(Long bomId, String bomMeterType);
    
    abstract Boolean addNewMeterByBomId(Long bomId, String meterBomType, String meterManufacturer, String meterSerial, String meterModel, String meterType);

    abstract Boolean addNewMeterByBomId(Long bomId, String meterBomType,String meterSerial, String meterAmi, Long meterManufacturerId, Long meterModelId, Long meterProtocolId, Long meterRoomId, Long meterFloorId);
    
    abstract void updateMTRDetail(BOMMeterDTO dto)throws DataAccessException;
    
    abstract void updateRequestStatus(String mmsReference, Integer status);
    
    abstract Boolean delete(Long id);
    
    abstract void updateMTRAll(List<BOMMeterDTO> deleteDTOs,List<BOMMeterDTO> addDTOs);
    
    abstract UtilityGatewayMeterSemantics findGtwAndMtrSemanticsByBomId(Long bomId);
}
