/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.buses;

import ae.etisalatdigital.commonUtils.exception.BusinessException;
import ae.etisalatdigital.iot.ops.utility.sync.beans.installation.UtilityGatewayMeterSemantics;
import ae.etisalatdigital.iot.ops.utility.sync.daos.BOMMeterDAO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.BOMMeters;
import java.util.Date;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.joda.time.DateTime;


/**
 *
 * @author au_mobility
 */
@Stateless
public class BOMMeterBus {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Inject
    private BOMMeterDAO bomDao;
    
    public List<BOMMeters> findAll(){
        return bomDao.findAll();
    }
    
    public List<BOMMeterDTO> findAllByBomId(Long bomId){
        return bomDao.findAllByBomId(bomId);
    }
    
    public List<BOMMeterDTO> addNewMeterByBomId(Long bomId, String meterBomType, String meterManufacturer, String meterSerial, String meterModel, String meterType){
        if( bomDao.addNewMeterByBomId(bomId, meterBomType, meterManufacturer, meterSerial, meterModel, meterType)){
           
            if(meterBomType.equalsIgnoreCase("water"))
            {
                return findAllByBomIdForWaterType(bomId);
            }else
            {
                return findAllByBomIdForElectricType(bomId);       
            }
        
        }
        return null;
    }
    
    
    public List<BOMMeterDTO> findAllByBomIdForElectricType(Long bomId){
        return bomDao.findAllByBomIdAndBomMeterType(bomId, "ELECTRIC");
    }
    
    public List<BOMMeterDTO> findAllByBomIdForWaterType(Long bomId){
        return bomDao.findAllByBomIdAndBomMeterType(bomId, "WATER");
    }
   
    public List<BOMMeterDTO> deleteMeter(Long meterId, Long bomId) throws BusinessException {
        if(meterId == null) {
            throw new BusinessException("Null meter details ID");
        }
        
        if(bomDao.delete(meterId)){
            return bomDao.findAllByBomId(bomId);
        }
        return null;
    }
    
    public List<BOMMeterDTO> deleteMeter(Long meterId, Long bomId,String meterBomType) throws BusinessException {
        if(meterId == null) {
            throw new BusinessException("Null meter details ID");
        }
        
        if(bomDao.delete(meterId)){
            if(meterBomType.equalsIgnoreCase("water"))
            {
                return findAllByBomIdForWaterType(bomId);
            }else
            {
                return findAllByBomIdForElectricType(bomId);       
            }
        }
        return null;
    }
    
    public void updateMeterDetails(BOMMeterDTO dto){
        dto.setModifiedDate(DateTime.now().toDate());
        bomDao.updateMTRDetail(dto);
    }
  
    public void updateMeterAll(List<BOMMeterDTO> deleteDTOs,List<BOMMeterDTO> addDTOs){
        bomDao.updateMTRAll(deleteDTOs,addDTOs);
    }
    public UtilityGatewayMeterSemantics findGatewayAndMeterSemanticsByBomId(Long bomId){
        return bomDao.findGtwAndMtrSemanticsByBomId(bomId);
    }

}
