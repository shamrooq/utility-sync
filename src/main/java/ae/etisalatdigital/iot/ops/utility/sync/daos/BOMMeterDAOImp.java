/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.beans.installation.UtilityGatewayMeterSemantics;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.BOMMeters;

import java.math.BigInteger;
import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;

/**
 *
 * @author au_mobility
 */
@Stateless
public class BOMMeterDAOImp implements BOMMeterDAO {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    private final String reqGtwMeterSemanticsQry="select b.utility_number utilityNumber,bge.Serial_Number gatewaySerialNumber,bge.EST_GTW_ID gatewayId," +
        "bge.gtw_floor bge,bge.gtw_room,bm.bom_meter_id meterId,bm.METER_Serial meterSerialNumber,bm.BOM_METER_Type meterType," +
        "bm.METER_Model meterModel,bm.METER_Manufacturer meterManufacture,bm.METER_Room meterRoom,bm.METER_Floor meterFloor," +
        "bm.METER_GTW_ID meterGtwId,bm.METER_Floor_Id meterFloorId,bm.METER_Room_Id meterRoomId from bom b join BOM_Gateways_Est bge on b.BOM_ID=bge.BOM_ID join bom_meter bm on bge.BOM_ID=bm.BOM_ID and "+
        "bge.EST_GTW_ID=bm.METER_GTW_ID where b.BOM_ID = ?1";

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
    @Override
    public void updateMTRAll(List<BOMMeterDTO> deleteDTOs,List<BOMMeterDTO> addDTOs) throws DataAccessException {
        addDTOs.stream().map(bommdto -> {
            return mapAddMeters(bommdto);
        }).filter(bomMeter -> bomMeter!=null).forEachOrdered(bomMeter -> {
            entityManager.merge(bomMeter);
        });
        deleteDTOs.stream().map(bommdto -> {
            BOMMeters bomMeter=getEntity(bommdto.getId());
            if (null != bomMeter.getMeterGtwId()) {//if meter is already defined, only then remove the mapping
                bomMeter.setMeterGtwId(null);
                bomMeter.setModifiedDate(DateTime.now().toDate());
                return bomMeter;
            }
            else{
                return null;
            }
        }).filter(bomMeter -> null!=bomMeter).forEachOrdered(bomMeter -> {
            entityManager.merge(bomMeter);
        });
    }

    private BOMMeters mapAddMeters(BOMMeterDTO bommdto) {
        BOMMeters bomMeter=getEntity(bommdto.getId());
        //if meter is not defined with the given gateway, only then add the mapping
        if (null == bomMeter.getMeterGtwId() || !bomMeter.getMeterGtwId().equals(bommdto.getMeterGtwId())) {
            bomMeter.setMeterGtwId(bommdto.getMeterGtwId());
            bomMeter.setModifiedDate(DateTime.now().toDate());
            return bomMeter;
        } else {
            return null;
        }
    }
    @Override
    public UtilityGatewayMeterSemantics findGtwAndMtrSemanticsByBomId(Long bomId) {
        Query query = entityManager.createNativeQuery(reqGtwMeterSemanticsQry);
        query.setParameter(1, bomId);
        List<Object[]> gatewayMetersSemanticsList = query.getResultList();
        Set<BigInteger> gtwIds = new TreeSet<>();
        BOMGatewayEstDTO bomGtwDTO;
        BOMMeterDTO bomMeterDTO;
        Set<BOMGatewayEstDTO> gatewayEstDTOs = new TreeSet<>();
        Set<BOMMeterDTO> meterDTOs = new TreeSet<>();
        UtilityGatewayMeterSemantics gatewayMetersSemantics = new UtilityGatewayMeterSemantics();
        for (Object[] obj : gatewayMetersSemanticsList) {
            if (null != obj[0]) {
                gatewayMetersSemantics.setUtilityNumber(obj[0].toString());
            }
            bomGtwDTO = new BOMGatewayEstDTO();
            if (null != obj[1]) {
                bomGtwDTO.setSerialNumber(obj[1].toString());
            }
            if (null != obj[2]) {
                gtwIds.add(new BigInteger(obj[2].toString()));
                bomGtwDTO.setId(new BigInteger(obj[2].toString()));
            }
            if (null != obj[3]) {
                bomGtwDTO.setGatewayFloor(obj[3].toString());
            }
            if (null != obj[4]) {
                bomGtwDTO.setGatewayRoom(obj[4].toString());
            }
            bomMeterDTO = new BOMMeterDTO();
            if (null != obj[5]) {
                bomMeterDTO.setId(Long.valueOf(obj[5].toString()));
            }
            if (null != obj[6]) {
                bomMeterDTO.setMeterSerial(obj[6].toString());
            }
            if (null != obj[7]) {
                bomMeterDTO.setBomMeterType(obj[7].toString());
            }
            if (null != obj[8]) {
                bomMeterDTO.setMeterModel(obj[8].toString());
            }
            if (null != obj[9]) {
                bomMeterDTO.setMeterManufacturer(obj[9].toString());
            }
            if (null != obj[10]) {
                bomMeterDTO.setMeterFloor(obj[10].toString());
            }
            if (null != obj[11]) {
                bomMeterDTO.setMeterRoom(obj[11].toString());
            }
            if (null != obj[12]) {
                bomMeterDTO.setMeterGtwId(new BigInteger(obj[12].toString()));
            }
            gatewayEstDTOs.add(bomGtwDTO);
            meterDTOs.add(bomMeterDTO);
        }
        gatewayMetersSemantics.setGatewaySet(gatewayEstDTOs);
        Map gtwMeterMap = new TreeMap<BigInteger, Set<BOMMeterDTO>>();
        for (BigInteger gtwId : gtwIds) {
            for (BOMMeterDTO meterDTO : meterDTOs) {
                if (gtwId.equals(meterDTO.getMeterGtwId())) {
                    if(gtwMeterMap.get(gtwId)==null){
                        TreeSet<BOMMeterDTO> treeSet = new TreeSet<>();
                        treeSet.add(meterDTO);
                        gtwMeterMap.put(gtwId, treeSet);
                    }
                    else{
                        ((Set<BOMMeterDTO>)gtwMeterMap.get(gtwId)).add(meterDTO);
                    }
                }
            }
        }
        gatewayMetersSemantics.setGtwMeterMap(gtwMeterMap);
        return gatewayMetersSemantics;
    }

}
