/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.BOMMeters;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.modelmapper.ModelMapper;

/**
 *
 * @author au_mobility
 */
@Stateless
public class BOMMeterDAOImp implements BOMMeterDAO {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Override
    public List<BOMMeters> findAll() {
        return entityManager.createNamedQuery("BOMMeters.findAll", BOMMeters.class).getResultList();
    }
    
    @Override
    public List<BOMMeterDTO> findAllByBomId(Long bomId){
        TypedQuery<BOMMeterDTO> query;
        query = entityManager.createNamedQuery("BOMMeters.findAllByBOMID", BOMMeterDTO.class);
        query.setParameter("bomId", bomId);
              return  query.getResultList();
    }
    
    @Override
    public List<BOMMeterDTO> findAllByBomIdAndBomMeterType(Long bomId, String bomMeterType){
        TypedQuery<BOMMeterDTO> query;
        query = entityManager.createNamedQuery("BOMMeters.findAllByBomIdAndBomMetertype", BOMMeterDTO.class);
        query.setParameter("bomId", bomId);
        query.setParameter("bomMeterType", bomMeterType);
              return  query.getResultList();
    }
    
    @Override
    public void updateMTRDetail(BOMMeterDTO dto)throws DataAccessException {
        BOMMeters entity = getEntity(dto.getId());
        if(entity == null) {
            throw new DataAccessException("Incident not found");
        }
        new ModelMapper().map(dto, entity);
        
        entityManager.merge(entity);
    }
    
    
    
    private BOMMeters getEntity(Long id) {
        BOMMeters entity = null;
        
        try {
            entity = entityManager.find(BOMMeters.class, id);
        } catch(NoResultException ex) {
            return null;
        }
        
        return entity;
    }
    
    /* ***
    *
    *
    */
    
    private BOMMeters getEntity(String utilityNumber) {
        BOMMeters entity = null;
        
        try {
            entity = entityManager.find(BOMMeters.class, utilityNumber);
        } catch(NoResultException ex) {
            return null;
        }
        
        return entity;
    }
    
    @Override
    public void updateRequestStatus(String mmsReference, Integer status){
        TypedQuery<BOMMeters> query = entityManager.createNamedQuery("BOMMeters.updateRequestStatus", BOMMeters.class);
        query.setParameter("requestStatus", status);
        query.setParameter("mmsReference", mmsReference);

        int update = query.executeUpdate();

        if (update == 0) {
            throw new DataAccessException("Update Status failed");
        }
    }
    
    @Override
    public Boolean addNewMeterByBomId(Long bomId, String meterBomType, String meterManufacturer, String meterSerial, String meterModel, String meterType){
        
        try{
            entityManager.createNativeQuery("exec [dbo].[SP_InsertNewMeter] " + bomId + ",'"+meterBomType+"','"+meterManufacturer+"','"+meterModel+"',N'"+meterType+"','"+meterSerial+"',N'ACTIVE'").executeUpdate();
            return true;
        }catch(Exception exp){
            System.out.println("add New Meter Failed:"+exp.getMessage());
        }
        
        return false;
    }
    
    /**
     * 
     * @param bomId
     * @param meterBomType
     * @param meterSerial
     * @param meterAmi
     * @param meterManufacturerId
     * @param meterModelId
     * @param meterProtocolId
     * @param meterRoomId
     * @param meterFloorId
     * @return 
     */
    @Override
    public Boolean addNewMeterByBomId(Long bomId, String meterBomType,String meterSerial, String meterAmi, Long meterManufacturerId, Long meterModelId, Long meterProtocolId, Long meterRoomId, Long meterFloorId){
        try{
            entityManager.createNativeQuery("exec [dbo].[SP_InsertNewMeter] " + bomId + ",'"+meterBomType+"','"+meterSerial+"','"+meterAmi+"',"+meterManufacturerId+","+meterModelId+","+meterProtocolId+","+meterRoomId+","+meterFloorId+",N'ACTIVE'").executeUpdate();
            return true;
        }catch(Exception exp){
            System.out.println("add New Meter Failed:"+exp.getMessage());
        }
        
        return false;
    }
    
    
    @Override
    public Boolean delete(Long id) {
        try{
            Query query = entityManager.createNamedQuery("BOMMeters.DELETE");
            query.setParameter("id", id);
            query.executeUpdate();
            return true;
        }catch(Exception exp){
            System.out.println("add New Meter Failed:"+exp.getMessage());
        }
        
        return false;
    }
        
}
