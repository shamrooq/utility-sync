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
import ae.etisalatdigital.iot.ops.utility.sync.entities.BOMGatewaysEst;
import ae.etisalatdigital.iot.ops.utility.sync.entities.BOMMeters;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTFloor;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTRoom;
import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;

/**
 *
 * @author au_mobility
 */
@Stateless
public class BOMMeterDAOImp implements BOMMeterDAO {
    private static final Logger logger = Logger.getLogger(BOMGatewayEstDAOImp.class);
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
//    private final String reqGtwMeterSemanticsQry="select b.utilityNumber utilityNumber,"
//            + "bge.serialNumber gatewaySerialNumber,bge.id gatewayId," +
//        "bge.mstFloor gtwFloor,bge.mstRoom gtwRoom,bm.id meterId,bm.meterSerial meterSerialNumber,"
//            + "bm.bomMeterType meterType," +
//        "bm.mstMeterModel meterModel,bm.meterManufacturerModel meterManufacture,bm.mstRoom meterRoom,"
//            + "bm.mstFloor meterFloor," +
//        "bm.meterGateway.id meterGtwId from "
//            + "Boms b join BOMGatewaysEst bge on b.id=bge.bomId join "
//            + "BOMMeters bm on bge.bomId=bm.bomId and "+
//        "bge.id=bm.meterGateway.id where b.id = ?1 order by bge.mstFloor.id";

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
        return query.getResultList();
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
    public void updateMTRAll(BOMGatewayEstDTO gateway, List<BOMMeterDTO> deleteDTOs, List<BOMMeterDTO> addDTOs) {
        try{
            //entityManager.getTransaction().begin();
            addDTOs.stream().map(bommdto -> {
                return mapAddMeters(gateway,bommdto);
            }).filter(bomMeter -> bomMeter != null).forEachOrdered(bomMeter -> {
                entityManager.merge(bomMeter);
            });
            deleteDTOs.stream().map(bommdto -> {
                BOMMeters bomMeter = getEntity(bommdto.getId());
                if (null != bomMeter.getMeterGateway()) {//if meter is already defined, only then remove the mapping
                    bomMeter.setMeterGateway(null);
                    bomMeter.setModifiedDate(DateTime.now().toDate());
                    return bomMeter;
                } else {
                    return null;
                }
            }).filter(bomMeter -> null != bomMeter).forEachOrdered(bomMeter -> {
                entityManager.merge(bomMeter);
            });
            //entityManager.getTransaction().commit();
        }
        catch(PersistenceException pe){
            //entityManager.getTransaction().rollback();
            logger.error("Exception "+pe.getMessage());
        }
    }

    private BOMMeters mapAddMeters(BOMGatewayEstDTO gateway, BOMMeterDTO bommdto) {
        BOMMeters bomMeter=getEntity(bommdto.getId());
        //if meter is not defined with the given gateway, only then add the mapping
        if (null == bomMeter.getMeterGateway() || !bomMeter.getMeterGateway().getId().equals(bommdto.getMeterGateway().getId())) {
            if(null==bomMeter.getMeterGateway()){
                bomMeter.setMeterGateway(new BOMGatewaysEst());
            }
            bomMeter.getMeterGateway().setId(gateway.getId());
            bomMeter.setModifiedDate(DateTime.now().toDate());
            return bomMeter;
        } else {
            return null;
        }
    }

    /*@Override
    public UtilityGatewayMeterSemantics findGtwAndMtrSemanticsByBomId(Long bomId) {
        //Query query = entityManager.createNativeQuery(reqGtwMeterSemanticsQry);
        Query query = entityManager.createQuery(reqGtwMeterSemanticsQry);
        query.setParameter(1, bomId);
        List<Object[]> gatewayMetersSemanticsList = query.getResultList();
        Set<BigInteger> gtwIds = new TreeSet<>();
        BOMGatewayEstDTO bomGtwDTO;
        BOMMeterDTO bomMeterDTO;
        Set<BOMGatewayEstDTO> gatewayEstDTOSet = new TreeSet<>();
        Set<BOMMeterDTO> meterDTOs = new TreeSet<>();
        UtilityGatewayMeterSemantics gatewayMetersSemantics = new UtilityGatewayMeterSemantics();
        Map<Long,Set<BOMGatewayEstDTO>> floorGtwMap=new HashMap<>();
        Map<String,MSTFloor> floorMap=new HashMap<>();
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
                MSTFloor mstFloor = (MSTFloor)obj[3];
                if(floorGtwMap.get(mstFloor.getId())==null){
                    floorGtwMap.put(mstFloor.getId(),new TreeSet<>());
                    floorMap.put(mstFloor.getFloorCode(), mstFloor);
                }
                bomGtwDTO.setGatewayFloor(mstFloor.getFloorCode());
                bomGtwDTO.setGatewayFloorId(mstFloor.getId());
                floorGtwMap.get(mstFloor.getId()).add(bomGtwDTO);
            }
            if (null != obj[4]) {
                MSTRoom mstRoom = (MSTRoom)obj[4];
                bomGtwDTO.setGatewayRoom(mstRoom.getRoomCode());
                bomGtwDTO.setGatewayRoomId(mstRoom.getId());
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
                MSTMeterModel meterModel = (MSTMeterModel)obj[8];
                bomMeterDTO.setMeterModel(meterModel.getModelTitle());
            }
            if (null != obj[9]) {
                MSTMeterManufacturer meterManufacturer = (MSTMeterManufacturer)obj[9];
                bomMeterDTO.setMeterManufacturer(meterManufacturer.getManufacturerName());
            }
            if (null != obj[10]) {
                MSTRoom mstRoom = (MSTRoom)obj[10];
                bomMeterDTO.setMeterRoom(mstRoom.getRoomCode());
            }
            if (null != obj[11]) {
                MSTFloor mstFloor = (MSTFloor)obj[11];
                bomMeterDTO.setMeterFloor(mstFloor.getFloorCode());
            }
            if (null != obj[12]) {
                bomMeterDTO.setMeterGtwId(new BigInteger(obj[12].toString()));
            }
            gatewayEstDTOSet.add(bomGtwDTO);
            meterDTOs.add(bomMeterDTO);
        }
        if(floorMap.size()>0){
            gatewayMetersSemantics.setFloorMap(floorMap);
        }
        gatewayMetersSemantics.setGatewayFloors(floorGtwMap);
        gatewayMetersSemantics.setGatewaySet(gatewayEstDTOSet);
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
    }*/

    @Override
    public UtilityGatewayMeterSemantics findGtwAndMtrSemanticsByBomId(Long bomId) {
        //Query query = entityManager.createNativeQuery(reqGtwMeterSemanticsQry);
        TypedQuery<BOMGatewaysEst> query = entityManager.createQuery("select bge FROM BOMGatewaysEst bge where bge.bom.id = ?1 order by bge.mstFloor.id",BOMGatewaysEst.class);
        query.setParameter(1, bomId);
        List<BOMGatewaysEst> gatewayMetersSemanticsList = query.getResultList();
        //Set<BigInteger> gtwIds = new TreeSet<>();
        BOMGatewayEstDTO bomGtwDTO;
        //BOMMeterDTO bomMeterDTO;
        Set<BOMGatewayEstDTO> gatewayEstDTOSet = new TreeSet<>();
        //Set<BOMMeterDTO> meterDTOs = new TreeSet<>();
        UtilityGatewayMeterSemantics gatewayMetersSemantics = new UtilityGatewayMeterSemantics();
        Map<Long,Set<BOMGatewayEstDTO>> floorGtwMap=new HashMap<>();
        Map<String,MSTFloor> floorMap=new HashMap<>();
        for (BOMGatewaysEst gateway : gatewayMetersSemanticsList) {
            if (null != gateway.getBom()) {
                gatewayMetersSemantics.setUtilityNumber(gateway.getBom().getUtilityNumber());
            }
            bomGtwDTO = new BOMGatewayEstDTO.Builder(gateway.getId(), gateway.getBom().getId())
                    .serialNumber(gateway.getSerialNumber())
                    .floor(gateway.getMstFloor())
                    .room(gateway.getMstRoom())
                    .meters(gateway.getBomMeterList())
                    .signalStrength(gateway.getSignalStrength(), gateway.getSignalStrengthIndicator())
                    .build();
            //gtwIds.add(gateway.getId());
            if (null != gateway.getMstFloor()) {
                MSTFloor mstFloor = gateway.getMstFloor();
                if(floorGtwMap.get(mstFloor.getId())==null){
                    floorGtwMap.put(mstFloor.getId(),new TreeSet<>());
                    floorMap.put(mstFloor.getFloorCode(), mstFloor);
                }
                bomGtwDTO.setGatewayFloor(mstFloor.getFloorCode());
                bomGtwDTO.setGatewayFloorId(mstFloor.getId());
                floorGtwMap.get(mstFloor.getId()).add(bomGtwDTO);
            }
            if (null != gateway.getMstRoom()) {
                MSTRoom mstRoom = gateway.getMstRoom();
                bomGtwDTO.setGatewayRoom(mstRoom.getRoomCode());
                bomGtwDTO.setGatewayRoomId(mstRoom.getId());
            }
            gatewayEstDTOSet.add(bomGtwDTO);
        }
        if(floorMap.size()>0){
            floorMap = floorMap.entrySet().stream().sorted(Map.Entry.comparingByKey((t1, t2) -> {
                return -1;
            })).collect(Collectors.toMap(
                    Map.Entry::getKey,Map.Entry::getValue,(oldValue,newValue)-> oldValue,LinkedHashMap::new));
            gatewayMetersSemantics.setFloorMap(floorMap);
        }
        floorGtwMap = floorGtwMap.entrySet().stream().sorted(Map.Entry.comparingByKey((t1, t2) -> {
            return -1;
        })).collect(Collectors.toMap(
                Map.Entry::getKey,Map.Entry::getValue,(oldValue,newValue)-> oldValue,LinkedHashMap::new));
        gatewayMetersSemantics.setGatewayFloors(floorGtwMap);
        gatewayMetersSemantics.setGatewaySet(gatewayEstDTOSet);
        //Map gtwMeterMap = new TreeMap<BigInteger, Set<BOMMeterDTO>>();
        return gatewayMetersSemantics;
    }

}
