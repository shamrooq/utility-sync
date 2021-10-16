/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.buses;

import ae.etisalatdigital.commonUtils.exception.BusinessException;
import ae.etisalatdigital.iot.ops.utility.sync.daos.BOMGatewayEstDAO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.BOMGatewaysEst;

import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author au_mobility
 */
@Stateless
public class BOMGatewayEstBus {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Inject
    private BOMGatewayEstDAO bomDao;
    
    public List<BOMGatewayEstDTO> findAll(){
        return bomDao.findAll();
    }
    
    public List<BOMGatewayEstDTO> findAllByBomId(Long bomId){
        return bomDao.findAllByBomId(bomId);
    }

    public List<BOMGatewayEstDTO> findSomeByBomId(Long bomId){
        return bomDao.findSomeByBomId(bomId);
    }

    public List<BOMGatewayEstDTO> addNewGatewayEstByBomId(Long bomId, String gatewayBomType, String proposedGateType, int noOfGateways, int metersPerGateway,Double cableLength,Long gatewayRoomId, Long gatewayFloorId, Boolean powerIntruption, Long signalStrength, Boolean antenaRequired){
        if( bomDao.addNewGatewayEstByBomId(bomId, gatewayBomType, proposedGateType, noOfGateways, metersPerGateway,cableLength,gatewayRoomId,gatewayFloorId,powerIntruption,signalStrength,antenaRequired)){
           
            return findAllByBomId(bomId);
        
        }
        return null;
    }

    public List<BOMGatewayEstDTO> addNewGatewayEstByBomId(Long bomId, String gatewayBomType, String proposedGateType, int noOfGateways, int metersPerGateway,String csbleEtimation,String gatewayLocation){
        if( bomDao.addNewGatewayEstByBomId(bomId, gatewayBomType, proposedGateType, noOfGateways, metersPerGateway,csbleEtimation,gatewayLocation)){

            return findAllByBomId(bomId);

        }
        return null;
    }

    
   
    public List<BOMGatewayEstDTO> deleteGateway(Long gatewayId, Long bomId) throws BusinessException {
        if(gatewayId == null) {
            throw new BusinessException("Null meter details ID");
        }
        
        if(bomDao.delete(gatewayId)){
            return bomDao.findAllByBomId(bomId);
        }
        return null;
    }
    
    public List<BOMGatewayEstDTO> deleteMeter(Long meterId, Long bomId,String meterBomType) throws BusinessException {
        if(meterId == null) {
            throw new BusinessException("Null meter details ID");
        }
        
        if(bomDao.delete(meterId)){
            
        }
        return null;
    }
    
    public void updateGatewayDetails(BOMGatewayEstDTO dto){
        bomDao.updateGatewayDetail(dto);
    }
  
}
